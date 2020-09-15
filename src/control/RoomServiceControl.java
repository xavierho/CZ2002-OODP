package control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import entity.Guest;
import entity.MenuItem;
import entity.Reservation;
import entity.Room;
import entity.RoomService;
import enums.orderStatus;
import enums.reservationStatus;

public class RoomServiceControl extends CoreUtilities{
	private static ArrayList<Reservation> reservationList;
	private static ArrayList<RoomService> roomserviceList;
	private static ArrayList<RoomService> checkoutroomserviceList;
	
	public RoomServiceControl() {
		roomserviceList = new ArrayList<RoomService>();
		checkoutroomserviceList = new ArrayList<RoomService>();
		reservationList = new ArrayList<Reservation>();
	}
	
	Scanner sc = new Scanner(System.in);
	String choice;
	
	public void createDetails() throws ParseException {
		GuestControl guestControl =  new GuestControl();
		MenuItemControl menuItemControl = new MenuItemControl();
		RoomControl roomControl = new RoomControl();
		
		RoomService roomService = new RoomService();
		MenuItem menuItem = new MenuItem();
		Guest guest = new Guest();
		Room room = new Room();
		// DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

		Date orderDate = new Date();
		orderDate = (Date) dateFormat.parse(dateFormat.format(orderDate));

			
		String remarks = "";
		int menuItemID = 0;
		int check = 0;
		
		System.out.println("Ordering Room Service =====================\n");
		reservationList = Database.getReservation();
		
		do {
		guest = guestControl.getDetails();
		room = roomControl.getDetails();
		
			for(int i=0;i<reservationList.size();i++) {
				Reservation reservation = reservationList.get(i);
				if((guest.getguestID().equals(reservation.getGuest().getguestID())) 
						&& (room.getRoomFloor() == reservation.getRoom().getRoomFloor()) 
						&& (room.getRoomNumber() == reservation.getRoom().getRoomNumber())
						&& (reservation.getReservationStatus().equals(reservationStatus.CheckedIn))) {
					roomService.setGuest(guest);
					roomService.setRoom(room);
					
					menuItemControl.printDetails();
	
					System.out.print("Enter MenuItem ID: ");
					menuItemID = sc.nextInt();
					menuItem = menuItemControl.searchMenuItem(menuItemID);
					roomService.setItem(menuItem);
					sc.nextLine();
									
					System.out.print("\nEnter Remarks: ");
					remarks = sc.nextLine();
					
					do {
						do {
							System.out.println("Confirm Service Order?");
							System.out.println("(1) Yes   (2) No");
							System.out.print("Input: ");
							choice = sc.nextLine();
							if (!isInteger(choice))
								System.out.println("Please enter a number.\n");
						} while (!isInteger(choice));
						if(Integer.parseInt(choice) == 1) {
							roomserviceList = Database.getRoomService();
							roomService.setOrderDate(orderDate);
							roomService.setRemarks(remarks);
							roomService.setOrderStatus(orderStatus.Confirmed);
							roomService.setServiceID(roomserviceList.size() +1);
							Database.addRoomService(roomService);
							System.out.println("Success! Guest order stored.\n");
						}
						else if (Integer.parseInt(choice) == 2){
							System.out.println("Service order not saved.\n");
						}
						else if (Integer.parseInt(choice) != 1 && Integer.parseInt(choice) != 2) 
						{
							System.out.println("Please enter a valid choice.\n");
						} 
					}while (Integer.parseInt(choice)!= 1 && Integer.parseInt(choice) != 2);
					
					check = 1;
					break;
				}
			}
			if(check == 0) {
				System.out.println("Please enter valid Guest and Room. ");
				System.out.println("This may happen due to various reasons. ");
				System.out.println("(1) Guest does not exist.");
				System.out.println("(2) Guest has checked out.");
				System.out.println("(3) Room does not exist.");
				System.out.println("(4) Room is not occupied.");

			}
		}while(check == 0);
	
	}
	
	public void updateDetails() {	
		int roomServiceID = 0;
		
		printDetails();
		
		System.out.print("Enter Room Service ID: ");
		roomServiceID = sc.nextInt();
		
		roomserviceList = Database.getRoomService();
		
		for(int i=0;i<roomserviceList.size();i++) {
			RoomService updaters= (RoomService) roomserviceList.get(i);
			
			if(roomServiceID == updaters.getServiceID()) {
				
				System.out.println("(1) Update Order Status To Preparing");
				System.out.println("(2) Update Order Status To Delivered");
				
					System.out.print("Enter Your Choice: ");
					System.out.println("-----------------------------------------------");
					int choice = intOptionParser(0, 2);
				
				switch(choice) {
				case 1:
					updaters.setOrderStatus(orderStatus.Preparing);
					break;
				case 2:
					updaters.setOrderStatus(orderStatus.Delivered);
					break;
				}
				roomserviceList.set(i, updaters);
				Database.saveRoomService(roomserviceList);
				System.out.println();
				System.out.println("Success! Guest order updated.");
				break;
			}
		}
	}
	
	public ArrayList<RoomService> searchRoomService(Guest guest, Room room) {
		roomserviceList = Database.getRoomService();
		
		for(int i=0; i<roomserviceList.size();i++) {
			RoomService searchroomservice = roomserviceList.get(i);
			
			if((guest.getguestID().equals(searchroomservice.getGuest().getguestID())) 
					&& (room.getRoomFloor() == searchroomservice.getRoom().getRoomFloor()) 
					&& (room.getRoomNumber() == searchroomservice.getRoom().getRoomNumber())) {
				checkoutroomserviceList.add(searchroomservice);
			}
		}
		return checkoutroomserviceList;
	}
	
	public void printDetails() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		roomserviceList = Database.getRoomService();
		TableList tl = new TableList(7, "ID", "Order Time", "Guest Name", "Room No.", "Item Name", "Remarks", "Status").withUnicode(true);
		for (int i = 0; i < roomserviceList.size(); i++) {
			RoomService rs = (RoomService) roomserviceList.get(i);
			tl.addRow(Integer.toString(rs.getServiceID()),  dateFormat.format(rs.getOrderDate()), rs.getGuest().getName(), 
			"0" + Integer.toString(rs.getRoom().getRoomFloor()) + "-0" + Integer.toString(rs.getRoom().getRoomNumber()), rs.getItem().getItemName(), rs.getRemarks(),rs.getOrderStatus().toString());
		}
		tl.print();
	}
}
