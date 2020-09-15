package control;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Room;
import enums.bedType;
import enums.roomType;
import enums.status;
import enums.withView;

public class RoomControl extends CoreUtilities implements RoomControlInterface{
	
	Scanner sc = new Scanner(System.in);
	public int option;
	boolean check = false;
	private static ArrayList<Room> roomList;
	
	public RoomControl() {
		roomList = new ArrayList <Room>();
	}
	
	public void createDetails()
	{
		System.out.println("Creating Room =====================");
		Room room = new Room();
		room = addRoom(room);  
		System.out.println("Please key in more details.");
		addRoomRate(room);
		addRoomType(room);
		addBedType(room);
		addRoomView(room);
		addRoomWifi(room);
		addRoomSmoking(room);
		
		Database.addRoom(room);
		System.out.println("Success! Room created.\n");
	}

	public void updateDetails()
	{
		Room room = new Room();
		System.out.println("Updating Room ======================");
		printDetails();
		System.out.println("Which Room would you like to update?");
		room = getDetails();
		do {
				System.out.println("Which attribute would you like to update?");
				System.out.println("(1) Room Rate");
				System.out.println("(2) Room Type");
				System.out.println("(3) Bed Type");
				System.out.println("(4) Room View");
				System.out.println("(5) Room Wifi");
				System.out.println("(6) Room Smoking");
				
				int option = intOptionParser(1, 6);
				
				switch(option) {
				case 1:
					addRoomRate(room);
					break;
				case 2:
					addRoomType(room);
					break;
				case 3:
					addBedType(room);
					break;
				case 4:
					addRoomView(room);
					break;
				case 5:
					addRoomWifi(room);				
					break;
				case 6:
					addRoomSmoking(room);
					break;
				default:
					break;
				}
				} while (continuePrompt("updating", "Room")); 

		roomList = Database.getRoom();
		for (int i = 0; i < roomList.size(); i++) {
			Room roomCur = (Room) roomList.get(i);
			if ((room.getRoomNumber()==roomCur.getRoomNumber()) && (room.getRoomFloor() == roomCur.getRoomFloor())) {
				roomList.set(i, room);
				break;
			}
		}
		Database.saveRoom(roomList);
		System.out.println("Success! Room details updated.\n");

	}

	// updates room status
	public void updateRoomStatus(Room room, int type)
	{
		roomList = Database.getRoom();
		for(int i = 0; i < roomList.size();i++)
		{
			Room cur = (Room) roomList.get(i);
			if(room.getRoomNumber() == cur.getRoomNumber()
			&& room.getRoomFloor() == cur.getRoomFloor() )

			{
				switch(type)
				{
				case 1:
					cur.setStatus(status.Vacant);
					roomList.set(i, cur);
					System.out.print("Success! Room status set to Vacant. \n");
					break;
				case 2:
					cur.setStatus(status.Occupied);
					roomList.set(i, cur);
					System.out.print("Success! Room status set to Occupied. \n");
					break;
				case 3:
					cur.setStatus(status.Reserved);
					roomList.set(i, cur);
					System.out.print("Success! Room status set to Reserved \n");
					break;
				case 4:
					cur.setStatus(status.UnderMaintenance);
					roomList.set(i, cur);
					System.out.print("Success! Room status set to Under Maintenance \n");
					break;
				default:
					break;
				}
				
				}
			}
		Database.saveRoom(roomList);
		}

	// maintenance functionality
	public void updateMaintenance()
	{
		Room room = new Room();		
		room = getDetails();
		System.out.println("Please update maintenance status.");
		// get input
		do {
			System.out.println("(1) Under Maintenance (2) Maintenance Completed ");
			try {
				System.out.print("Input: ");
				option = sc.nextInt();
				if (option  < 1 || option > 2)
					System.out.println("You have not selected option between 1 & 2. Please try again.\n");
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.\n");
				sc.nextInt();
			}
		} while (option < 1 || option > 2);

		// update room status
		switch (option) {
		case 1:
			updateRoomStatus(room, 4);
			break;
		case 2:
			updateRoomStatus(room, 1);
			break;
		}
		System.out.println("Success! Room Maintenance updated.\n");
		
	}
		
	// print room status
	public void printRoomStatus()
	{
		sortRooms();
		roomList = Database.getRoom();
		String[] status = { "Vacant", "Reserved", "Occupied", "UnderMaintenance" };
		int count = 0;
		// display different room status
		for (int i = 0; i < status.length; i++) {
			System.out.println(status[i] + ": ");
			System.out.print("    Rooms: ");
			count = 0;
			// display room numbers
			for (int j = 0; j < roomList.size(); j++) {
				Room room = (Room) roomList.get(j);

				if (room.getStatus().toString().equals(status[i])) {
					count++;//why count++;
					System.out.print("0"+room.getRoomFloor()+"-0"+room.getRoomNumber()+" | ");
				}
			}
			System.out.println("\n    Total: "+count);
			System.out.println("");
		}

		System.out.println("");
	}


	public void roomOccupancyReport()
	{
		sortRooms();
		roomList = Database.getRoom();
		ArrayList<Room> SingleVacant = new ArrayList<Room>();
		ArrayList<Room> DoubleVacant = new ArrayList<Room>();
		ArrayList<Room> DeluxeVacant = new ArrayList<Room>();
		ArrayList<Room> VIPVacant = new ArrayList<Room>();
		int SingleCount = 0;
		int DoubleCount = 0;
		int DeluxeCount = 0;
		int VIPCount = 0;
		
		int SingleVCount = 0;
		int DoubleVCount = 0;
		int DeluxeVCount = 0;
		int VIPVCount = 0;
		roomType type;
		status roomStatus;
		for(int i = 0; i <roomList.size();i++)
		{
			Room room = (Room) roomList.get(i);
			type = room.getRoomType();
			if(type.equals(roomType.Single))
			{
				SingleCount++;
			}
			else if(type.equals(roomType.Double))
			{
				DoubleCount++;
			}
			else if(type.equals(roomType.Deluxe))
			{
				DeluxeCount++;
			}
			else if(type.equals(roomType.VIPSuite))
			{
				VIPCount++;
			}
		}
		for(int j = 0; j <roomList.size();j++)
		{
			Room room = (Room) roomList.get(j);
			type = room.getRoomType();
			roomStatus = room.getStatus();
			
			if(type.equals(roomType.Single)&&roomStatus.equals(status.Vacant))
			{
				SingleVacant.add(room);
				SingleVCount++;
			}
			else if(type.equals(roomType.Double)&&roomStatus.equals(status.Vacant))
			{
				DoubleVacant.add(room);
				DoubleVCount++;
			}
			else if(type.equals(roomType.Deluxe)&&roomStatus.equals(status.Vacant))
			{
				DeluxeVacant.add(room);
				DeluxeVCount++;
			}
			else if (type.equals(roomType.VIPSuite)&&roomStatus.equals(status.Vacant))
			{
				VIPVacant.add(room);
				VIPVCount++;

			}
		}
	
		System.out.println("Single Rooms Report: " + SingleVCount + "/" + SingleCount);
		if (SingleVacant.size() == 0) {
			System.out.println("    No vacant rooms available.\n" );
		}
		else {
			System.out.println("    Vacant Single Rooms" );
			System.out.print("    Rooms: ");
			for(int i = 0; i < SingleVacant.size();i++)
			{

				System.out.print("0" + SingleVacant.get(i).getRoomFloor() + "-"+ "0" + SingleVacant.get(i).getRoomNumber()+ " | " );
			}
			System.out.println();
			System.out.println();

		}
		System.out.println("Double Rooms Report: " + DoubleVCount + "/" + DoubleCount);
		if (DoubleVacant.size() == 0) {
			System.out.println("    No vacant rooms available.\n" );
		}
		else {
		System.out.println("    Vacant Double Rooms" );
		System.out.print("    Rooms: ");
		for(int i = 0; i < DoubleVacant.size();i++)
		{
			System.out.print("0" + DoubleVacant.get(i).getRoomFloor() + "-" + "0" + DoubleVacant.get(i).getRoomNumber()+ " | " );
		}
		System.out.println();
		System.out.println();

	}
	System.out.println("Deluxe Rooms Report: " + DeluxeVCount + "/" + DeluxeCount);
	if (DeluxeVacant.size() == 0) {
		System.out.println("    No vacant rooms available.\n" );
	}
	else {
		System.out.println("    Vacant Deluxe Rooms" );
		System.out.print("    Rooms: ");

		for(int i = 0; i < DeluxeVacant.size();i++)
		{
			System.out.print("0" + DeluxeVacant.get(i).getRoomFloor() + "-"+ "0" +DeluxeVacant.get(i).getRoomNumber()+ " | " );
		}
		System.out.println();
		System.out.println();

	}
	System.out.println("VIP Suites Rooms Report: " + VIPVCount + "/" + VIPCount);
	if (VIPVacant.size() == 0) {
		System.out.println("    No vacant rooms available.\n" );
	}
	else {
		System.out.println("    Vacant VIP Rooms" );
		System.out.print("    Rooms: ");

		for(int i = 0; i < VIPVacant.size();i++)
		{
			System.out.print("0" + VIPVacant.get(i).getRoomFloor() + "-"+ "0" +VIPVacant.get(i).getRoomNumber()+ " | " );
		}
		System.out.println();
		System.out.println();

	}
	}

	public Room addRoom(Room room) {
		System.out.println("Please enter Room Number in the following format.");
		int floor;
		int unit;
		String roomNumber;
		boolean check= false;
		do {
		try {
			System.out.print("Floor-Unit (ie 02-01): ");
			roomNumber = sc.nextLine();
			//check format is valid first
			if (roomNumber.length() !=5) {
				throw new InputMismatchException();
			}
			if(isInteger(roomNumber.substring(0,2)) && isInteger(roomNumber.substring(3,5))) {
				floor = Integer.parseInt(roomNumber.substring(0,2));
				unit = Integer.parseInt(roomNumber.substring(3,5));
				room = checkUnitExists(floor, unit);
			}
			else {
				throw new InputMismatchException();
			}
			if(room != null) { // room exists
				throw new Exception(); 
			}
			if (room == null) { // room does not exist
				Room room1 = new Room();
				room1.setRoomFloor(floor);
				room1.setRoomNumber(unit);
				room1.setStatus(status.Vacant);
				check = true;
				return room1;
			}
		} catch(InputMismatchException e) {
			System.out.println("You have entered an invalid Room Number. Please try again !");
			System.out.println("Please key in the Room Number again:");
		} catch (Exception e) {
			System.out.println("Room exists.");
			System.out.println("Please key in the Room Number again:");
		}
	} while (check == false);
	return null;
}



	public Room checkUnitExists(int floor, int unit) {
		roomList = Database.getRoom();
		for (int i = 0; i < roomList.size(); i++) {
			Room room1 = (Room) roomList.get(i);
			if (room1.getRoomFloor()== floor && room1.getRoomNumber()==unit) {
					return room1;
			}
		}
		return null;
	}

	public Room getDetails() 	{
		System.out.println("Please enter Room Number in the following format.");
		int floor;
		int unit;
		String roomNumber;
		boolean check= false;
		Room room = new Room();
		do {
			try {
				System.out.print("Floor-Unit (ie 02-01): \n");
				roomNumber = sc.nextLine();
				if (roomNumber.length() !=5) {
					throw new InputMismatchException();
				}
				if(isInteger(roomNumber.substring(0,2)) && isInteger(roomNumber.substring(3,5))) {
					floor = Integer.parseInt(roomNumber.substring(0,2));
					unit = Integer.parseInt(roomNumber.substring(3,5));
					room = checkUnitExists(floor, unit);
				}
				else {
					throw new InputMismatchException();
				}
				if(room != null) { // if room exists
					return room;
				}
				if (room == null ){ // if room does not exist
					throw new Exception();
				}
			} catch(InputMismatchException e) {
				check = false;
				System.out.println("\nYou have entered an invalid floor number. Please try again !");
				System.out.println("Please key in the floor number again:\n");
				
			} catch (Exception e) {
				System.out.println("Room does not exist. Please try again.\n");
			}
		} while (check == false);
		return null;

	}

	public void addRoomRate(Room room)
	{
		do {
			System.out.println("-------------------------------------");
			System.out.print("Room Rate: ");
			try {
				double price = sc.nextDouble();
				room.setRoomRate(price);
				check = true;
			}
			catch(InputMismatchException e)
			{
				System.out.println("You have entered an invalid input. Please try again.\n");
				sc.next();
			}
		}while(check != true);
	}
	
	public void addRoomType(Room room)
	{
		System.out.println("-------------------------------------");
		System.out.println("Room Type: ");
		System.out.println("(1) Single   (2) Double   (3) Deluxe   (4) VIP Suite");
		int option = intOptionParser(1,3); 
		switch(option) {
		case 1:
			room.setRoomType(roomType.Single);
			System.out.print("Success! Room type set to Single. \n");
			break;
		case 2:
			room.setRoomType(roomType.Double);
			System.out.print("Success! Room type set to Double. \n");
			break;
		case 3:
			room.setRoomType(roomType.Deluxe);
			System.out.print("Success! Room type set to Deluxe. \n");
			break;
		case 4:
			room.setRoomType(roomType.VIPSuite);
			System.out.print("Success! Room Type set to VIP. \n");
			break;
		}
	}
	public void addBedType(Room room)
	{
		System.out.println("-------------------------------------");
		System.out.println("Bed Type: ");
		// Room Type
		System.out.println("(1) Single   (2) Double   (3) Master");
		int option = intOptionParser(1,3); 
		
		switch(option) {
		case 1:
			room.setBedType(bedType.SingleBed);
			System.out.print("Success! Bed Type is Single. \n");
			break;
		case 2:
			room.setBedType(bedType.DoubleBeds);
			System.out.print("Success! Bed Type is Double. \n");
			break;
		case 3:
			room.setBedType(bedType.MasterBed);
			System.out.print("Success! Bed Type is Master. \n");
			break;
		}
		
	}
	
	public void addRoomView(Room room)
	{
		System.out.println("-------------------------------------");
		System.out.println("Room View: ");
		// Room View
		System.out.println("(1) Sea   (2) City   (3) Park   (4) None");
		int option = intOptionParser(1,4); 
		switch(option) {
		case 1:
			room.setWithView(withView.Sea);
			System.out.print("Success! Room View set to Sea. \n");
			break;
		case 2:
			room.setWithView(withView.City);
			System.out.print("Success! Room View set to City. \n");
			break;
		case 3:
			room.setWithView(withView.Park);
			System.out.print("Success! Room View set to Park. \n");
			break;
		case 4:
			room.setWithView(withView.Nothing);
			System.out.print("Success! Room View set to None. \n");
			break;
		}
	}
	
	public void addRoomWifi(Room room)
	{
		System.out.println("-------------------------------------");
		System.out.println("WiFi Enabled?" );
		System.out.println("(1) Yes (2) No" );

		int option = intOptionParser(1, 2); 
		switch(option)
		{
			case 1:
				room.setWifiEnabled(true);
				System.out.println("Success! WiFi enabled.");
				break;
			case 2:
				room.setWifiEnabled(false);
				System.out.println("Success! WiFi not enabled.");
				break;
		}
	}
	
	public void addRoomSmoking(Room room)
	{
		System.out.println("-------------------------------------");
		System.out.println("Smoking allowed?" );
		System.out.println("(1) Yes (2) No" );		
			//wifi enabled
			int option = intOptionParser(1, 2); 
			switch(option)
			{
				case 1:
					room.setSmoking(true);
					System.out.println("Success! Smoking allowed.");
					break;
				case 2:
					room.setSmoking(false);
					System.out.println("Success! Smoking not allowed.");
					break;
			}

	}

	public void printDetails() {
		sortRooms();
		roomList = Database.getRoom();
		if (roomList.size() == 0) {
			System.out.println("\nNo Rooms ");
			return ;
		}
		
		System.out.println("\nAll Rooms: ");
		TableList tl = new TableList(8, "Room Number", "Room Type", "BedType", "Rate", "With View","WiFi","Smoking","Status").withUnicode(true);
		for(int index = 0; index < roomList.size();index++)
		{
			Room room = (Room) roomList.get(index);
			tl.addRow("0"+room.getRoomFloor()+"-0"+room.getRoomNumber(), room.getRoomType().toString(), room.getBedType().toString(), Double.toString(room.getRoomRate()), room.getWithView().toString(), Boolean.toString(room.isWifiEnabled()), Boolean.toString(room.isSmoking()), room.getStatus().toString());  
		}
		tl.print();
	}

	public void sortRooms() {
		roomList = Database.getRoom();
		for(int index1 = 0; index1 < roomList.size();index1++)	{
			for(int index2 = 0; index2 < roomList.size();index2++)	{
				Room room1 = roomList.get(index1);
				int room1Nu = room1.getRoomFloor()*10+room1.getRoomNumber();
				Room room2 = roomList.get(index2);
				int room2Nu = room2.getRoomFloor()*10+room2.getRoomNumber();

				if (room1Nu < room2Nu) {
					Room roomHolder = room1;
					// int indexHolder = index1;
					roomList.set(index1,room2);
					roomList.set(index2,roomHolder);
				}
		}
		}
		Database.saveRoom(roomList);
	}

	public void printRooms(boolean available)
	{
		sortRooms();
		roomList = Database.getRoom();
		if (roomList.size() == 0) {
			System.out.println("\nNo Rooms ");
			return ;
		}
		System.out.println("\nRooms Available: ");
		TableList tl = new TableList(7, "Room Number", "Room Type", "BedType", "Rate", "With View","WiFi","Smoking").withUnicode(true);
		for(int index = 0; index < roomList.size();index++)
		{
			Room room = (Room) roomList.get(index);
			if(room.getStatus().equals(status.Vacant) && !room.getStatus().equals(status.UnderMaintenance))
			{
				tl.addRow("0"+room.getRoomFloor()+"-0"+room.getRoomNumber(), room.getRoomType().toString(), room.getBedType().toString(), Double.toString(room.getRoomRate()), room.getWithView().toString(), Boolean.toString(room.isWifiEnabled()), Boolean.toString(room.isSmoking()));  
			}
		}
		tl.print();

	}
}