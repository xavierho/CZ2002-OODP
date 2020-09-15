package app;

import java.text.ParseException;

import control.CoreUtilities;
import control.Database;
import control.GuestControl;
import control.MenuItemControl;
import control.PaymentControl;
import control.ReservationControl;
import control.RoomControl;
import control.RoomServiceControl;

public class HotelApp extends CoreUtilities {
	public static void main(String[] aArgs) throws Exception {

		Database initialise = new Database();
		// initialise.start();
		initialise.read();

		initialiseApp();
	}

	public static void initialiseApp() throws ParseException {
		// initialize controllers

		GuestControl guestControl = new GuestControl();
		ReservationControl reservationControl = new ReservationControl();
		RoomControl roomControl = new RoomControl();
		RoomServiceControl roomServiceControl = new RoomServiceControl();
		MenuItemControl menuItemControl = new MenuItemControl();
		PaymentControl paymentControl = new PaymentControl();

		System.out.println("=================================================");
		System.out.println("Welcome to Hotel Reservation and Payment System.");
		System.out.println("What would you like to configure?");
		System.out.println("(1) Guest");
		System.out.println("(2) Room");
		System.out.println("(3) Reservation");
		System.out.println("(4) Room Service");
		System.out.println("(5) Menu Item");
		System.out.println("(6) Payment and Check Out");
		System.out.println("(0) Exit");
		System.out.println("-------------------------------------------------");
		int choice = intOptionParser(0, 6);
		System.out.print("-------------------------------------------------\n\n");
		switch (choice) {
			case 1:
				guestOption(guestControl);
				break;
			case 2:
				reservationControl.noShow();
				roomOption(roomControl);
				break;
			case 4:
				roomServiceOption(roomServiceControl);
				break;
			case 3:
				reservationControl.noShow();
				reservationOption(reservationControl);
				break;
			case 6:
				paymentOption(paymentControl);
				break;
			case 5:
				menuItemOption(menuItemControl);
				break;
			case 0:
				System.out.println("Thank you.");
				System.exit(0);
			default:
				break;
		}
		initialiseApp();

	}

	public static void guestOption(GuestControl guestControl) throws ParseException {
		do {
			System.out.println("-----------------------------------------------");

		System.out.println("What would you like to configure?");
		System.out.println("(1) Add New Guest");
		System.out.println("(2) Update Guest Details");
		System.out.println("(3) Search Guest");
		System.out.println("(0) Return");
			System.out.println("-----------------------------------------------");
			int choice = intOptionParser(0, 3);
			switch (choice) {
				case 1:
					guestControl.createDetails();
					break;
				case 2:
					guestControl.updateDetails();
					break;
				case 3:
					guestControl.searchGuestByKeyword();
					break;
				case 0:
					System.out.println("Returning back to main menu...\n");
					initialiseApp();
					break;
				default:
					break;
			}
		} while (continuePrompt("configuring", "Guest"));

	}

	public static void roomOption(RoomControl roomControl) throws ParseException {
		do {
			System.out.println("-----------------------------------------------");

		System.out.println("What would you like to configure?");
		System.out.println("(1) Add Room");
		System.out.println("(2) Update Room Details");
		System.out.println("(3) Update Room Maintenance");
		System.out.println("(4) Print All Rooms");
		System.out.println("(5) Print Available Rooms");
		System.out.println("(6) Print Occupancy Report by Room Type");
		System.out.println("(7) Print Occupancy Report by Room Status");
		System.out.println("(0) Return");
		System.out.println("-----------------------------------------------");
			int choice = intOptionParser(0, 7);
			switch (choice) {
				case 1:
					roomControl.createDetails();
					break;
				case 2:
					roomControl.updateDetails();
					break;
				case 3:
					roomControl.updateMaintenance();
					break;
				case 4:
					roomControl.printDetails();
					break;
				case 5:
					roomControl.printRooms(true);
					break;
				case 6:
					roomControl.roomOccupancyReport();
					break;
				case 7:
					roomControl.printRoomStatus();
					break;
				case 0:
					System.out.println("Returning back to main menu...\n");
					initialiseApp();
					break;
				default:
					break;
			}
		} while (continuePrompt("configuring", "Room"));

	}

	public static void reservationOption(ReservationControl reservationControl) throws ParseException {
		do {
			System.out.println("-----------------------------------------------");

		System.out.println("What would you like to configure?");
		System.out.println("(1) Check In");
		System.out.println("(2) Create Reservation");
		System.out.println("(3) Update Reservation");
		System.out.println("(4) Delete Reservation");
		System.out.println("(5) Print Reservation");
		System.out.println("(0) Return");
			System.out.println("-----------------------------------------------");
			int choice = intOptionParser(0, 5);

			switch (choice) {
				case 1:
					reservationControl.checkIn();
					break;
				case 2:
					reservationControl.createDetails();
					break;
				case 3:
					reservationControl.updateDetails();
					break;
				case 4:
					reservationControl.removeDetails();
					break;
				case 5:
					reservationControl.printResDetailsByGuestID();
					break;
				case 0:
					System.out.println("Returning back to main menu...\n");
					initialiseApp();
					break;
				default:
					break;
			}
		} while (continuePrompt("configuring", "Reservation"));

	}

	public static void menuItemOption(MenuItemControl menuItemControl) throws ParseException {
		do {
			menuItemControl.printDetails();
			System.out.println("-----------------------------------------------");
			System.out.println("What would you like to configure?");
			System.out.println("(1) Create New Menu Item");
			System.out.println("(2) Update Menu Item");
			System.out.println("(3) Remove Menu Item");
			System.out.println("(0) Return");
			System.out.println("-----------------------------------------------");
			int choice = intOptionParser(0, 3);
			switch (choice) {
				case 1:
					menuItemControl.createDetails();
					break;
				case 2:
					menuItemControl.updateDetails();
					break;
				case 3:
					menuItemControl.removeDetails();
					break;
				case 0:
					System.out.println("Returning back to main menu...\n");
					initialiseApp();
					break;
				default:
					break;
			}
		} while (continuePrompt("configuring", "Menu Item"));

	}

	public static void roomServiceOption(RoomServiceControl roomServiceControl) throws ParseException {
		do {
			System.out.println("-----------------------------------------------");

	System.out.println("What would you like to configure?");
	System.out.println("(1) Order Room Service");
	System.out.println("(2) Update Room Service Status");
	System.out.println("(3) Print Room Service");
	System.out.println("(0) Return");

	System.out.println("-----------------------------------------------");
	int choice = intOptionParser(0, 3);

	switch (choice) {
		case 1:
			roomServiceControl.createDetails();
			break;
		case 2:
			roomServiceControl.updateDetails();
			break;
		case 3:
			roomServiceControl.printDetails();
			break;
		case 0:
			System.out.println("Returning back to main menu...\n");
			initialiseApp();
			break;
		default:
			break;
	}
} while (continuePrompt("configuring", "Room Service"));


}

public static void paymentOption(PaymentControl paymentControl) {
	System.out.println("Initialising Payment System...");
	paymentControl.createPayment();

}



	
}
