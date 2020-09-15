package control;

import entity.MenuItem;
import entity.Guest;
import entity.Payment;
import entity.Reservation;
import entity.Room;
import entity.RoomService;
import enums.bedType;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class Database {
	
	private static ArrayList<Room> roomList;
	
	private static ArrayList<Guest> guestList;
	
	private static ArrayList<Reservation> reservationList;
	
	private static ArrayList<MenuItem> menuitemList;
	
	private static ArrayList<RoomService> roomserviceList;
	
	private static ArrayList<Payment> paymentList;
	
	public Database() {
		roomList = new ArrayList<Room>();
		guestList = new ArrayList<Guest>();
		reservationList = new ArrayList<Reservation>();
		menuitemList = new ArrayList<MenuItem>();
		roomserviceList = new ArrayList<RoomService>();
		paymentList = new ArrayList<Payment>();
	}
	
	public void read() throws Exception {
		roomList = readRoom();
		guestList = readGuest();
		reservationList = readReservation();
		menuitemList = readMenuItem();
		roomserviceList = readRoomService();
	}

	public void start() {
		Guest guest1 = new Guest("XAVIER HO", "S9726288H", "559 Choa Chu Kang North 6", "Male", "Singapore",
				"Singaporean", 11112222, 87492641, enums.idType.Passport);
		Guest guest2 = new Guest("GONG HAI", "S1234567A", "123 Nanyang Crescent North 3", "Male", "China", "Chinese",
				11113333, 81234567, enums.idType.Passport);
		Guest guest3 = new Guest("HE WANRU", "S1111111A", "456 Nanyang Crescent West 3", "Female", "Singapore",
				"Singaporean", 11115555, 91234556, enums.idType.DrivingLicense);
			

		guestList.add(guest1);
		guestList.add(guest2);
		guestList.add(guest3);

		saveGuest(guestList);

		Room room1 = new Room(2, 1, 40, enums.roomType.Single, bedType.SingleBed, enums.status.Occupied,
		enums.withView.Nothing, true, false);
		Room room2 = new Room(2, 2, 60, enums.roomType.Double, enums.bedType.DoubleBeds, enums.status.Occupied,
		enums.withView.Park, true, false);
		Room room3 = new Room(2, 3, 80, enums.roomType.Deluxe, enums.bedType.MasterBed, enums.status.Vacant,
		enums.withView.Sea, true, false);
		Room room5 = new Room(3, 1, 40, enums.roomType.Single, enums.bedType.SingleBed, enums.status.Vacant,
		enums.withView.Nothing, true, false);
		Room room6 = new Room(3, 2, 60, enums.roomType.Double, enums.bedType.DoubleBeds, enums.status.Vacant,
		enums.withView.Park, true, false);
		Room room7 = new Room(3, 3, 80, enums.roomType.Deluxe, enums.bedType.MasterBed, enums.status.Vacant,
		enums.withView.Sea, true, false);
		Room room8 = new Room(4, 1, 40, enums.roomType.Single, enums.bedType.SingleBed, enums.status.Vacant,
		enums.withView.Nothing, true, false);
		Room room9 = new Room(4, 2, 60, enums.roomType.Double, enums.bedType.DoubleBeds, enums.status.Vacant,
		enums.withView.Park, true, false);
		Room room10 = new Room(4, 3, 80, enums.roomType.Deluxe, enums.bedType.MasterBed, enums.status.Vacant,
		enums.withView.Sea, true, false);
		Room room11 = new Room(5, 1, 40, enums.roomType.Single, enums.bedType.SingleBed, enums.status.Vacant,
		enums.withView.Nothing, true, false);
		Room room12 = new Room(5, 2, 60, enums.roomType.Double, enums.bedType.DoubleBeds, enums.status.Vacant,
		enums.withView.Park, true, false);
		Room room13 = new Room(5, 3, 80, enums.roomType.Deluxe, enums.bedType.MasterBed, enums.status.Vacant,
		enums.withView.Sea, true, false);
		Room room14 = new Room(6, 1, 40, enums.roomType.Single, enums.bedType.SingleBed, enums.status.Vacant,
		enums.withView.Nothing, true, false);
		Room room15 = new Room(6, 2, 60, enums.roomType.Double, enums.bedType.DoubleBeds, enums.status.Vacant,
		enums.withView.Park, true, false);
		Room room16 = new Room(6, 3, 80, enums.roomType.Deluxe, enums.bedType.MasterBed, enums.status.Vacant,
		enums.withView.Sea, true, false);
		Room room4 = new Room(7, 1, 200, enums.roomType.VIPSuite, enums.bedType.MasterBed, enums.status.Vacant,
		enums.withView.Sea, true, true);	
		Room room17 = new Room(7, 2, 200, enums.roomType.VIPSuite, enums.bedType.MasterBed, enums.status.Vacant,
		enums.withView.Sea, true, true);
		Room room18 = new Room(7, 3, 200, enums.roomType.VIPSuite, enums.bedType.MasterBed, enums.status.Vacant,
		enums.withView.Sea, true, true);
		Room room19 = new Room(7, 4, 200, enums.roomType.VIPSuite, enums.bedType.MasterBed, enums.status.Vacant,
		enums.withView.Sea, true, true);


		roomList.add(room1);
		roomList.add(room2);
		roomList.add(room3);
		roomList.add(room5);
		roomList.add(room6);
		roomList.add(room7);
		roomList.add(room8);
		roomList.add(room9);
		roomList.add(room10);
		roomList.add(room11);
		roomList.add(room12);
		roomList.add(room13);
		roomList.add(room14);
		roomList.add(room15);
		roomList.add(room16);
		roomList.add(room4);
		roomList.add(room17);
		roomList.add(room18);
		roomList.add(room19);

		saveRoom(roomList);
		
		MenuItem menuitem1 = new MenuItem("Beef Burger","Super Spicy",4,1);
		MenuItem menuitem2 = new MenuItem("Chicken Rice","Hainanese Delicacy",12,2);
		MenuItem menuitem3 = new MenuItem("Nasi Lemak","Local Delight",10,3);
		MenuItem menuitem4 = new MenuItem("Beef Horfun","Local Delight!",14,4);
		MenuItem menuitem5 = new MenuItem("Bubble Tea","Going once, going twice, gone!",3,5);


		menuitemList.add(menuitem1);
		menuitemList.add(menuitem2);
		menuitemList.add(menuitem3);
		menuitemList.add(menuitem4);
		menuitemList.add(menuitem5);

		saveMenuItem(menuitemList);

		DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

		try {
			Reservation res1 = new Reservation("20200401143020", guest1, room1, enums.billingType.Cash,
					(Date) dateFormat2.parse("04/01/2020"), (Date) dateFormat2.parse("05/04/2020"), 2, 2,
					enums.reservationStatus.CheckedIn, enums.bookingType.WalkIn);
			Reservation res2 = new Reservation("20200401120000", guest2, room2, enums.billingType.CreditCard,
				(Date) dateFormat2.parse("04/25/2020"), (Date) dateFormat2.parse("04/29/2020"), 1, 3,
				enums.reservationStatus.InWaitlist, enums.bookingType.PreBooking);
			Reservation res3 = new Reservation("20200412140000", guest3, room3, enums.billingType.CreditCard,
				(Date) dateFormat2.parse("04/23/2020"), (Date) dateFormat2.parse("05/01/2020"), 1, 3,
				enums.reservationStatus.InWaitlist, enums.bookingType.PreBooking);
				
			RoomService roomservice1 = new RoomService(1, "More Spicy", (Date) dateFormat3.parse("2020/04/05 09:30:20"),
					enums.orderStatus.Confirmed, menuitem1, guest3, room1);
			
			Payment payment1 = new Payment(res1,502.2,7,300,15,10,(Date) dateFormat2.parse("04/02/2020"));
			
			reservationList.add(res1);
			reservationList.add(res2);
			reservationList.add(res3);
			saveReservation(reservationList);
			roomserviceList.add(roomservice1);
			saveRoomService(roomserviceList);
			paymentList.add(payment1);
			savePayment(paymentList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
	}
	
	public static void saveGuest(ArrayList<Guest> guestList)
	{
		try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream("Guest.dat")))
		{
			objOutput.writeObject(guestList);
		}
		catch (IOException e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static void saveRoom(ArrayList<Room> roomList)
	{
		try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream("Room.dat")))
		{
			objOutput.writeObject(roomList);
		}
		catch (IOException e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static void saveReservation(ArrayList<Reservation> reservationList)
	{
		try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream("Reservation.dat")))
		{
			objOutput.writeObject(reservationList);
		}
		catch (IOException e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static void saveMenuItem(ArrayList<MenuItem> menuitemList)
	{
		try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream("MenuItem.dat")))
		{
			objOutput.writeObject(menuitemList);
		}
		catch (IOException e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static void saveRoomService(ArrayList<RoomService> roomserviceList)
	{
		try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream("RoomService.dat")))
		{
			objOutput.writeObject(roomserviceList);
		}
		catch (IOException e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static void savePayment(ArrayList<Payment> paymentList)
	{
		try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream("Payment.dat")))
		{
			objOutput.writeObject(paymentList);
		}
		catch (IOException e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static ArrayList<Guest> readGuest() throws Exception
	{
		ArrayList<Guest> guestList2 = new ArrayList<Guest>();
		
		try
	    {
			FileInputStream fis = new FileInputStream("Guest.dat");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        guestList2 = (ArrayList<Guest>) ois.readObject();     
	    } 
	    catch (IOException ioe) 
	    {
	    	ioe.printStackTrace();
	        return guestList2;
	    } 
	    catch (ClassNotFoundException c) 
	    {
	        System.out.println("Class not found");
	        c.printStackTrace();
	        return guestList2;
	    }
		
		return guestList2;
	}
	
	public static ArrayList<Room> readRoom() throws Exception
	{
		ArrayList<Room> roomList2 = new ArrayList<Room>();
		
		try
	    {
			FileInputStream fis = new FileInputStream("Room.dat");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        roomList2 = (ArrayList) ois.readObject();     
	    } 
	    catch (IOException ioe) 
	    {
	    	ioe.printStackTrace();
	        return roomList2;
	    } 
	    catch (ClassNotFoundException c) 
	    {
	        System.out.println("Class not found");
	        c.printStackTrace();
	        return roomList2;
	    }
		
		return roomList2;
	}
	
	public static ArrayList<Reservation> readReservation() throws Exception
	{
		ArrayList<Reservation> reservationList2 = new ArrayList<Reservation>();
		
		try
	    {
			FileInputStream fis = new FileInputStream("Reservation.dat");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        reservationList2 = (ArrayList) ois.readObject();     
	    } 
	    catch (IOException ioe) 
	    {
	    	ioe.printStackTrace();
	        return reservationList2;
	    } 
	    catch (ClassNotFoundException c) 
	    {
	        System.out.println("Class not found");
	        c.printStackTrace();
	        return reservationList2;
	    }
		
		return reservationList2;
	}
	
	public static ArrayList<MenuItem> readMenuItem() throws Exception
	{
		ArrayList<MenuItem> menuitemList2 = new ArrayList<MenuItem>();
		
		try
	    {
			FileInputStream fis = new FileInputStream("MenuItem.dat");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        menuitemList2 = (ArrayList) ois.readObject();     
	    } 
	    catch (IOException ioe) 
	    {
	    	ioe.printStackTrace();
	        return menuitemList2;
	    } 
	    catch (ClassNotFoundException c) 
	    {
	        System.out.println("Class not found");
	        c.printStackTrace();
	        return menuitemList2;
	    }
		
		return menuitemList2;
	}
	
	public static ArrayList<RoomService> readRoomService() throws Exception
	{
		ArrayList<RoomService> roomserviceList2 = new ArrayList<RoomService>();
		
		try
	    {
			FileInputStream fis = new FileInputStream("RoomService.dat");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        roomserviceList2 = (ArrayList) ois.readObject();     
	    } 
	    catch (IOException ioe) 
	    {
	    	ioe.printStackTrace();
	        return roomserviceList2;
	    } 
	    catch (ClassNotFoundException c) 
	    {
	        System.out.println("Class not found");
	        c.printStackTrace();
	        return roomserviceList2;
	    }
		
		return roomserviceList2;
	}
	
	public static ArrayList<Payment> readPayment() throws Exception
	{
		ArrayList<Payment> paymentList2 = new ArrayList<Payment>();
		
		try
	    {
			FileInputStream fis = new FileInputStream("Payment.dat");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        paymentList2 = (ArrayList) ois.readObject();     
	    } 
	    catch (IOException ioe) 
	    {
	    	ioe.printStackTrace();
	        return paymentList2;
	    } 
	    catch (ClassNotFoundException c) 
	    {
	        System.out.println("Class not found");
	        c.printStackTrace();
	        return paymentList2;
	    }
		
		return paymentList2;
	}
	
	public static void addGuest(Guest g)
	{
		guestList.add(g);
		saveGuest(guestList);
	}
	
	public static void addRoom(Room r)
	{
		roomList.add(r);
		saveRoom(roomList);
	}
	
	public static void addReservation(Reservation re)
	{
		reservationList.add(re);
		saveReservation(reservationList);
	}
	
	public static void addMenuItem(MenuItem m)
	{
		menuitemList.add(m);
		saveMenuItem(menuitemList);
	}
	
	public static void addRoomService(RoomService rs)
	{
		roomserviceList.add(rs);
		saveRoomService(roomserviceList);
	}
	
	public static void addPayment(Payment p)
	{
		paymentList.add(p);
		savePayment(paymentList);
	}
	
	public static ArrayList<Guest> getGuest()
	{
		return guestList;
	}
	
	public static ArrayList<Room> getRoom()
	{
		return roomList;
	}
	
	public static ArrayList<Reservation> getReservation()
	{
		return reservationList;
	}
	
	public static ArrayList<MenuItem> getMenuItem()
	{
		return menuitemList;
	}
	
	public static ArrayList<RoomService> getRoomService()
	{
		return roomserviceList;
	}
	
	public static ArrayList<Payment> getPayment()
	{
		return paymentList;
	}
}
