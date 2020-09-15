package control;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Guest;
import entity.Payment;
import entity.Reservation;
import entity.Room;
import entity.RoomService;
import enums.orderStatus;
import enums.reservationStatus;



public class PaymentControl {

	Scanner sc = new Scanner(System.in);
	
	private static ArrayList<RoomService> checkoutroomserviceList;
	
	public PaymentControl() {
	}
	
	private ReservationControl reservControl = new ReservationControl();
	private RoomControl roomControl = new RoomControl();
	private RoomServiceControl rsControl = new RoomServiceControl();

	DecimalFormat decimalFormat = new DecimalFormat(".##");
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	public void createPayment() {
		Payment payment = new Payment();
		Guest guest = new Guest();
		Reservation reservation = new Reservation();
		Room room = new Room();
		
		double roomCharges = 0;
		int daysStayed = 0;
		final double GST = 0.07;
		double taxCharges = 0;
		double discount = 0;
		double totalService = 0;
		boolean valid = false;
		
		reservControl.printResDetailsByGuestID();
		reservation = reservControl.getDetails();
		guest = reservation.getGuest();
		room = reservation.getRoom();
		if (reservation.getReservationStatus().equals(reservationStatus.CheckedOut)) {
			System.out.println("Error. Guest has already Checked Out and Paid.");
			return;
		}
		daysStayed = (int) ((reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime())
				/ (24 * 60 * 60 * 1000));
		roomCharges = calculateRoomCharges(reservation, room, daysStayed);

		taxCharges = roomCharges * GST;
		taxCharges = Double.valueOf(decimalFormat.format(taxCharges));

		checkoutroomserviceList = rsControl.searchRoomService(guest, room); 
		for (int i = 0; i < checkoutroomserviceList.size(); i++) {
			if (checkoutroomserviceList.get(i).getOrderStatus().equals(orderStatus.Delivered)) {
			totalService += checkoutroomserviceList.get(i).getItem().getItemPrice();
			}
		}

		
		// discount
		do {
			valid = false;
			System.out.print("Enter any discount (%): ");
			try {
				discount = sc.nextInt();
				if (discount < 0 || discount > 50)
					System.out.println("Discounts should be less than 50%. Please try again.");
				else
					valid = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!valid);

		
		// calculate overall total amount.
		double afterTaxCharge = roomCharges + totalService + taxCharges;
		double totalCharges = Double.valueOf(decimalFormat.format(afterTaxCharge - ((afterTaxCharge) * (discount / 100))));
		// create payment object to store all relevant data.
		 
		payment.setReservation(reservation);
		payment.setRoomCharges(roomCharges);
		payment.setTax(taxCharges);
		payment.setDiscount(discount);
		payment.setTotalBill(totalCharges);
		payment.setDate(new Date());
		
		Database.addPayment(payment);

		// update reservation and room status
		reservControl.updateReservationStatus(reservation, enums.reservationStatus.CheckedOut);
		roomControl.updateRoomStatus(room, 1);
		
		
		// display of invoice
		System.out.print("\nPayment made by " + guest.getName() + " by ");
		if (reservation.getBillingType().equals(enums.billingType.Cash))
			System.out.print("Cash. ");
		else
			System.out.print("Credit Card, " + guest.getCreditCardNumber() + "\nAddress: " + guest.getAddress());
		
		System.out.println();

		TableList tl = new TableList(2, "Invoice", " ").withUnicode(true);
		tl.addRow("Days Of Stay", Integer.toString(daysStayed));
		tl.addRow("Room Rate", Double.toString(roomCharges) +"0");
		tl.addRow("--------------------------", "--------------------------");
		tl.addRow("Room Service Items", Integer.toString(checkoutroomserviceList.size()));

		if (checkoutroomserviceList.size() > 0) {
			tl.addRow("--------------------------", "--------------------------");
			for (int i = 0; i < checkoutroomserviceList.size(); i++) {
				if (checkoutroomserviceList.get(i).getOrderStatus().equals(orderStatus.Delivered)) {
					tl.addRow(checkoutroomserviceList.get(i).getItem().getItemName(), Double.toString(checkoutroomserviceList.get(i).getItem().getItemPrice())+"0");
				}
			}
		}
		tl.addRow("--------------------------", "--------------------------");
		tl.addRow("Total Room Service Charges", Double.toString(totalService)+"0");
		tl.addRow("Tax", Double.toString(taxCharges)+"0");
		tl.addRow("Total Amount", Double.toString(totalCharges)+"0");
		tl.print();

	}

	// calculate total cost for guest
	private double calculateRoomCharges(Reservation res, Room r, int days) {
		double multiplier = 0;
		double charges = 0;
		double total = 0;
		Date date = res.getCheckInDate();
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("EEE dd MMM yyyy");

		TableList tl2 = new TableList(3, " ","Reservation Summary", " ").withUnicode(true);
		tl2.addRow("Room: "+"0"+r.getRoomFloor()+"-0"+r.getRoomNumber(), "Type: " + r.getRoomType().toString(), "Rate: "+Double.toString(r.getRoomRate())+"0");
		tl2.addRow("---------------", "--------------------", "--------------------");
		tl2.addRow("Day","Date", "Charges (S$)");
		tl2.addRow("---------------", "--------------------", "--------------------");

		for (int i = 1; i <= days; i++) {
			c.setTime(date);
			int day = c.get(Calendar.DAY_OF_WEEK);
			// Days of week indexed starting at 1
			if (day > 1 && day < 7)
				multiplier = 1; // Weekdays
			else
				multiplier = 1.5; // Weekends

			charges = r.getRoomRate() * multiplier;
			tl2.addRow(Integer.toString(i), df.format(date), Double.toString(charges)+"0");

			total += charges;

			c.add(Calendar.DATE, 1);
			date = c.getTime();
		}
		tl2.addRow("---------------", "--------------------", "--------------------");
		tl2.addRow("Total Charges", " ", Double.toString(total));
		tl2.print();

		return total;
	}
}