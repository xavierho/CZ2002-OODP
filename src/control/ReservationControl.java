package control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import app.HotelApp;
import entity.Guest;
import entity.Reservation;
import entity.Room;
import enums.bookingType;
import enums.reservationStatus;

public class ReservationControl extends CoreUtilities implements ReservationControlInterface {
	Scanner sc = new Scanner(System.in);
	
	private static ArrayList<Reservation> reservationList;
	public ReservationControl() {
		reservationList = new ArrayList<Reservation>();
		new ArrayList<Room>();
		new ArrayList<Guest>();
	}

	public void createDetails() {
		Room room = new Room();

		RoomControl roomControl = new RoomControl();
		GuestControl guestControl = new GuestControl();

		//set default values
		Reservation res = new Reservation();
		System.out.println("\nMaking Reservation ==================================");
		addReservationID(res);
		addBookingType(res);
		addGuest(res, guestControl);
		roomControl.printRooms(true);
		room = addRoomDetails(res);
		addBillingType(res);

		if (res.getBookingType().equals(bookingType.WalkIn)) {
			Date todayDate = new Date();
			res.setCheckInDate(todayDate);
			res.getRoom().setStatus(enums.status.Occupied);
		}
		else {
			addCheckInDate(res);
		}
		addCheckOutDate(res);
		addAdults(res);
		addChildren(res);
		printDetails(res);
		// save reservation
		// write Reservation record/s to file.
		Database.addReservation(res);
		roomControl.updateRoomStatus(room, 3);
		System.out.println("Success! Reservation created.\n");


	}

	public void updateDetails() {
		Reservation res = new Reservation();
		RoomControl roomControl = new RoomControl();

		System.out.println("\nUpdating Reservation ===============================");
		System.out.println("Enter Guest ID and Reservation ID to continue.");
		printResDetailsByGuestID();
		System.out.print("Input which Reservation to update.");
		res = getDetails();

		System.out.println("What would you like to update about Reservation " + res.getReservationID() + "?");
		System.out.println("(1) Room Unit");
		System.out.println("(2) Billing Type");
		System.out.println("(3) Check In Date");
		System.out.println("(4) Check Out Date");
		System.out.println("(5) Number of Adults staying");
		System.out.println("(6) Number of Children staying");
		System.out.println("(0) Return");

		do {
			System.out.println("-----------------------------------------------");
			int choice = intOptionParser(0, 7);

			switch (choice) {

				case 1:
				roomControl.printRooms(true);
				System.out.println("Which room would you like to change to?");
				addRoomDetails(res,1);
				break;
				case 2: 
				addBillingType(res);
				break;
				case 3: 
				if (res.getReservationStatus().equals(reservationStatus.CheckedIn)) {
					System.out.println("Guest has already checked in.\n");
					break;
				}
				addCheckInDate(res);
				break;
				case 4: 
				addCheckOutDate(res);
				break;
				case 5: 
				addAdults(res);
				break;
				case 6:
				addChildren(res);
				break;
				default:
					break;
				}
			} while(continuePrompt("updating","Reservation " + res.getReservationID()));

		Database.saveReservation(reservationList);
		// do i have to set(res,i) or something?

		System.out.println("Success! Reservation details updated!\n");
		try {
			HotelApp.initialiseApp();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	
	public void addReservationID(Reservation res) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String reservationID;
		Date date = new Date();
		reservationID = dateFormat.format(date);
		res.setReservationID(reservationID);
	}
	
		
	public void checkIn() {

		Reservation res = new Reservation();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date todayDate = new Date();

		System.out.println("Checking In ==================");
		printResDetailsByGuestID();
		System.out.print("Please input which Reservation you are checking in.");
		res = getDetails();
		if (res == null) {
			System.out.println("No such Reservation found. Guest was not checked in.\n");
		}
		if (res!= null) {
			// if Guest's check in date and today's date matches, he is allowed to check in.
			if (res.getReservationStatus()==reservationStatus.InWaitlist
			&& dateFormat.format(res.getCheckInDate()).equals(dateFormat.format(todayDate))) {
				res.setReservationStatus(reservationStatus.CheckedIn);
				System.out.println("Success! Guest checked in.\n");

			}
			// guest has already checked in
			else if (res.getReservationStatus()==reservationStatus.CheckedIn) {
				System.out.println("Error! Guest has already checked in.\n");
			}
			// if guest is not supposed to check in today
			else if (res.getReservationStatus()==reservationStatus.InWaitlist
			&& !dateFormat.format(res.getCheckInDate()).equals(dateFormat.format(todayDate))) {
				System.out.println("Error! It is not the day to check in.\n");
			}
			else if (res.getReservationStatus()==reservationStatus.CheckedOut) {
				System.out.println("Error! Guest has already checked out.\n");
			}
			else {
				System.out.println("Error! Reservation may have expired.\n");
			}
		}
		Database.saveReservation(reservationList);
	}
	
	
	
	public void noShow() { 
		RoomControl roomControl = new RoomControl();
		Room room = null;
		Date today = new Date();

		reservationList = Database.getReservation();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat dateFormat3 = new SimpleDateFormat("yyyyMMdd");
		
		if (reservationList.size() != 0) {
			// if guest's check in date is past today's date, meaning he has not checked in yet
			// expire the reservation
			for (int i = 0; i < reservationList.size(); i++) {
				Reservation res1 = (Reservation) reservationList.get(i);
				if ((dateFormat2.format(today)).equals(dateFormat2.format(res1.getCheckInDate()))
						&& res1.getReservationStatus().equals(reservationStatus.InWaitlist) 
								&& (Integer.parseInt(dateFormat.format(today).substring(8)) >120000)) {
								// reservation expires after 12pm when the guest has not checked in on the same day 
								updateReservationStatus(res1, reservationStatus.Expired);
					room = res1.getRoom();
					if (room != null) 
						roomControl.updateRoomStatus(room, 1);
				}
				else if ( Integer.parseInt(dateFormat3.format(today)) > Integer.parseInt(dateFormat3.format(res1.getCheckInDate()))
						&& res1.getReservationStatus().equals(reservationStatus.InWaitlist) ) {
				// reservation expires when the guest has not checked in one day after
					updateReservationStatus(res1, reservationStatus.Expired);
					room = res1.getRoom();
					if (room != null)
						roomControl.updateRoomStatus(room,1);
				}

			}
		}
		Database.saveReservation(reservationList);
	}
	
	
	public void removeDetails() {
		Room room = new Room();
		System.out.println("Deleting Reservation ================");
		printResDetailsByGuestID();
		System.out.print("Input Reservation ID to delete: ");
		Reservation resToDelete = getDetails();

		for (int i=0; i<reservationList.size(); i++ ) {
			Reservation res1 = reservationList.get(i);
			if (res1.getReservationID().equals(resToDelete.getReservationID())) {
				// can add check if guest ID tallies with reservation ID
				reservationList.remove(i);
				room = res1.getRoom();
				room.setStatus(enums.status.Vacant);
				System.out.println("Success! Reservation has been deleted.\n");
			}
		}
		Database.saveReservation(reservationList);
	}
	
	
	public void updateReservationStatus(Reservation reservation, reservationStatus updateResStatus) {
		reservationList = Database.getReservation();
		
		for (int i=0; i<reservationList.size();i++) {
			Reservation res1 = (Reservation) reservationList.get(i);
			if(res1.getReservationID() == reservation.getReservationID()) {
					res1.setReservationStatus(updateResStatus);
			}
		}
		
		Database.saveReservation(reservationList);
		
		System.out.println("Success! Reservation status updated to " + updateResStatus.toString() +"\n");
	}


	



	// gets reservation details by res ID
	public Reservation getDetails() {
		boolean parser = false;
		do {
			System.out.print("\nPlease enter Reservation ID: ");
			String input = sc.nextLine();
			reservationList = Database.getReservation();
			for (int i = 0; i < reservationList.size(); i++) {
				Reservation res1 = (Reservation) reservationList.get(i);
				if ( input.equals(res1.getReservationID()) ) {
					parser = true;
					return res1;
					}
				}
			if (!parser) {
				System.out.println("Reservation not found. Please try again.\n");
			}
			
		} while (!parser);
		return null;
	}

	public void printResDetailsByGuestID() {
		Guest guest = new Guest();
		Reservation reservation = new Reservation();
		Boolean parser = false;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		GuestControl guestControl = new GuestControl();
		reservationList = Database.getReservation();

		guest = guestControl.getDetails();
		reservation.setGuest(guest);
		TableList tl = new TableList(11, "Reservation ID", "Status","Name", "ID", "Check In", "Check Out", "Room No", "Room Type", "Room Status", " Adults", "Children").withUnicode(true);
		if (reservation!= null) {
			for (int i = 0; i < reservationList.size(); i++) {
				Reservation res = (Reservation) reservationList.get(i);
				if (res.getGuest().getguestID().equals(reservation.getGuest().getguestID())) {
						parser = true;
						// printReservationFlat(res1, tl);
						tl.addRow(res.getReservationID(), res.getReservationStatus().toString(), res.getGuest().getName(), res.getGuest().getguestID(), 
						dateFormat.format(res.getCheckInDate()), dateFormat.format(res.getCheckOutDate()), "0"+res.getRoom().getRoomFloor()+"-"+"0"+res.getRoom().getRoomNumber(), res.getRoom().getRoomType().toString(), res.getRoom().getStatus().toString(),
						Integer.toString(res.getNumOfAdults()), Integer.toString(res.getNumOfChildren()) );
				}
			}
			if (parser == false) {
				System.out.println("No Reservation exists. \n");
			}
			if (parser == true) {
			tl.print();
			}
		}
	}


	public void addBookingType(Reservation res)  {
		System.out.println("Booking Type: (1) Walk In (2) Prebooking");
		int choice = intOptionParser(1, 2);

		if (choice ==1) {
			res.setBookingType(enums.bookingType.WalkIn);
			res.setReservationStatus(reservationStatus.CheckedIn);
		}
		else if (choice ==2) {
			res.setBookingType(enums.bookingType.PreBooking);
			res.setReservationStatus(reservationStatus.InWaitlist);
		}


	}

	public Guest addGuest(Reservation res, GuestControl guestControl) {
		res.setGuest(guestControl.getDetails());
		return res.getGuest();
	}

	public Room addRoomDetails(Reservation res) {
		RoomControl roomControl = new RoomControl();
		Room room = new Room();
		room = roomControl.getDetails();
		res.setRoom(room);
		return res.getRoom();
	}

	public Room addRoomDetails(Reservation res, int update) {
		RoomControl roomControl = new RoomControl();
		Room room = new Room();

		room = roomControl.getDetails();

		System.out.print("Updating previous room...");
		roomControl.updateRoomStatus(res.getRoom(),1);
		
		res.setRoom(room);

		if (res.getReservationStatus().equals(reservationStatus.InWaitlist))
			roomControl.updateRoomStatus(res.getRoom(), 1);
		if (res.getReservationStatus().equals(reservationStatus.CheckedIn))
			roomControl.updateRoomStatus(res.getRoom(), 2);
		
		return res.getRoom();
	}

	public void addBillingType(Reservation res) {

		System.out.println("Billing Method: (1) Cash (2) Credit Card");
		try {
			System.out.print("Input: ");
			int billType = sc.nextInt();
			if (billType <1 || billType >2) {
				System.out.println("Please enter a valid option.");
			}
			if (billType ==1) {
				res.setBillingType(enums.billingType.Cash);
			}
			if (billType ==2) {
				res.setBillingType(enums.billingType.CreditCard);
			}
		} catch (InputMismatchException e) {
			// what is input mismatch exception?
			System.out.println("Please enter a valid option. Please try again.\n");
			sc.nextInt();
		}
	}

	public void addCheckInDate(Reservation res) {
		boolean parser = false;
		DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Check In Date: MM/DD/YYYY");
			try {
				System.out.print("Input: ");
				Date checkInDate = (Date) dateFormat2.parse(sc.nextLine());
				Date todayDate = new Date();
				todayDate = dateFormat2.parse(dateFormat2.format(todayDate));

				// todayDate = (Date) dateFormat2.parse(dateFormat2.format(todayDate));
				if (checkInDate.compareTo(todayDate)==0 ||  (checkInDate.compareTo(todayDate)>0)) {
					parser = true;
					res.setCheckInDate(checkInDate);
					if (checkInDate.compareTo(todayDate)==0) {
						res.setReservationStatus(reservationStatus.CheckedIn);
					}
				}
				else {
					parser = false;
					System.out.println("Date entered must be today or later. Please try again.\n");
				}
			} catch (ParseException e) {
				System.out.println("You have entered a invalid format. Please try again.\n");
			}
		} while (parser == false);
	}


	public void addCheckOutDate(Reservation res) {
		boolean parser = false;
		DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Check Out Date: MM/DD/YYYY");
			try {
				System.out.print("Input: ");
				Date checkOutDate = (Date) dateFormat2.parse(sc.nextLine());
				if(res.getCheckInDate().compareTo(checkOutDate) ==0 || res.getCheckInDate().compareTo(checkOutDate) >0) {
					parser = false;
					System.out.println("Date entered must be after Check In date. Please try again.\n");
				}
				else  {
					parser = true;
					res.setCheckOutDate(checkOutDate);
				}
			} catch (ParseException e) {
				System.out.println("You have entered a invalid  format. Please try again.\n");
			}
		} while (parser == false);
	}

	public void addAdults(Reservation res) {
		int numOfAdults;
		boolean parser = false;
		do {
			System.out.println("Number of Adult(s) checking in: ");
			try {
				System.out.print("Input: ");
				numOfAdults = sc.nextInt();
				res.setNumOfAdults(numOfAdults);
				parser = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (parser == false);
	}

	public void addChildren(Reservation res) {
		int numOfChildren;
		boolean parser = false;
		do {
			System.out.println("Number of Children checking in: ");
			try {
				System.out.print("Input: ");
				numOfChildren = sc.nextInt();
				res.setNumOfChildren(numOfChildren);
				parser = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (parser == false);
	}

	public void printDetails(Reservation res) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		TableList tl = new TableList(2, "Receipt", " ").withUnicode(true);
		tl.addRow("Reservation ID", res.getReservationID());
		tl.addRow("Name", res.getGuest().getName());
		tl.addRow("ID Number", res.getGuest().getguestID());
		tl.addRow("Room No." ,"0"+ res.getRoom().getRoomFloor() + "-" + "0" + res.getRoom().getRoomNumber());
		tl.addRow("Room Type", res.getRoom().getRoomType().toString());
		tl.addRow("Check In Date (MM/DD/YYYY)", dateFormat.format(res.getCheckInDate()));
		tl.addRow("Check Out Date (MM/DD/YYYY)", dateFormat.format(res.getCheckOutDate()));
		tl.addRow("Num of Adults", Integer.toString(res.getNumOfAdults()));
		tl.addRow("Num of Children", Integer.toString(res.getNumOfChildren()));
		tl.addRow("Reservation Status", res.getReservationStatus().toString());
		tl.print();
	}

	
	// public void sortReservations() {
	// 	reservationList = Database.getReservation();
	// 	for(int index1 = 0; index1 < reservationList.size();index1++)	{
	// 		for(int index2 = 0; index2 < reservationList.size();index2++)	{
	// 			Reservation res1 = reservationList.get(index1);
	// 			Reservation res2 = reservationList.get(index2);
	// 			if (Long.parseLong(res1.getReservationID()) < Long.parseLong(res2.getReservationID())) {
	// 				Reservation resHolder = res1;
	// 				reservationList.set(index1,res2);
	// 				reservationList.set(index2,resHolder);
	// 			}
	// 	}
	// 	}
	// 	Database.saveReservation(reservationList);
	// }



}
