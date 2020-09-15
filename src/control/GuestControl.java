package control;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Guest;
import enums.idType;

public class GuestControl extends CoreUtilities implements GuestControlInterface 
{
	Scanner sc = new Scanner(System.in);
	private static ArrayList<Guest> guestList;

	public GuestControl() {
		guestList = new ArrayList<Guest>();
	}
	
	// create new guest
	public void createDetails() 
	{
		System.out.println("\nGuest Registration ============================");
		
		Guest guest = new Guest();
		
		System.out.println("\nPlease enter the following information for guest.");

		addName(guest);
		addGender(guest);
		addIDType(guest);
		addNationality(guest);
		addAddress(guest);
		addCountry(guest);
		addMobileNumber(guest);
		addCreditCardNumber(guest);
		
		Database.addGuest(guest);		
		System.out.println("Success! Guest registered.\n");

	}
	
	//update current guest details
	public void updateDetails() 
	{
		System.out.println("\nUpdating Guest Information ====================");

		Guest guest = getDetails();
		printDetails(guest);
		System.out.println("What would you like to update about " + guest.getName()+ "?");
		System.out.println("(1) Name");
		System.out.println("(2) Gender");
		System.out.println("(3) ID Type and ID");
		System.out.println("(4) Nationality");
		System.out.println("(5) Address");
		System.out.println("(6) Country");
		System.out.println("(7) Mobile Number");
		System.out.println("(8) Credit Card Number");
		System.out.println("(0) Return");

		do {
			System.out.println("-----------------------------------------------");
			int choice = intOptionParser(0, 8);

			switch (choice) {
				case 1: 
					addName(guest);
					break;
				case 2:
					addGender(guest);
					break;
				case 3: 
					addIDType(guest);
					break;
				case 4: 
					addNationality(guest);
					break;
				case 5: 
					addAddress(guest);
					break;
				case 6: 
					addCountry(guest);
					break;
				case 7:
					addMobileNumber(guest);	
					break;
				case 8: 
					addCreditCardNumber(guest);
					break;
				default:
					break;
				}
			} while(continuePrompt("updating",guest.getName()));
			// Write Guest records to file

		Database.saveGuest(guestList);

		System.out.println("Success! Guest details updated!");

	}





		
	//search guest by name (keywords)
	public void searchGuestByKeyword ()
	{
		String inputName;
		Guest guest = new Guest();
		do {	
		do {
			System.out.print("\nPlease enter name of existing Guest.\n");
			inputName = sc.nextLine().toUpperCase();
	
			if (inputName.equals("")) 
				System.out.println("Please enter a valid name.\n");
	
		} while (inputName.equals("") );
		
		guestList = Database.getGuest();
		
		for (int i = 0; i < guestList.size(); i++)
		{
			Guest searchGuest = (Guest) guestList.get(i);
			if(searchGuest.getName().contains(inputName))
			{
				guest = searchGuest;
				System.out.print("Guest found.\n");
				printDetails(guest);	
				return;
			}	
		}
		System.out.print("Guest cannot be found.\n");
	} while (continuePrompt("searching", "Guest"));
	}

	
		
	// search guest by ID
	public Guest searchGuestById(Guest guest)
	{
		guestList = Database.getGuest();

		for (int i = 0; i < guestList.size(); i++) 
		{
			Guest searchguest = (Guest) guestList.get(i);

			if (guest.getguestID().equals(searchguest.getguestID()))
			{
				guest = searchguest;
				return guest;
			}
		}
		return null;
	}
	
	// retrieve particular guest details through guest ID
	public Guest getDetails() 
	{				
		Guest guest = new Guest();
		Guest checkGuest = new Guest();
		String id;
		do 
		{
			System.out.print("Please enter Guest ID: ");
			id = sc.nextLine().toUpperCase();
			guest.setguestID(id);
			checkGuest = searchGuestById(guest);
			
			if (checkGuest == null)
				System.out.println("Please enter a valid Guest ID");
			
		} while (checkGuest == null);
		
		return checkGuest;
	}

	// printing guest details when needed		
	public void printDetails(Guest guest) {
		TableList tl = new TableList(2, "Guest Details", " ").withUnicode(true);
		tl.addRow("Name", guest.getName());
		tl.addRow("Gender", guest.getGender().toString());
		tl.addRow("ID Type", guest.getIdType().toString());
		tl.addRow("Guest ID", guest.getguestID());
		tl.addRow("Credit Card No.", Long.toString(guest.getCreditCardNumber()));
		tl.addRow("Address" , guest.getAddress());
		tl.addRow("Country" , guest.getCountry());
		tl.addRow("Nationality" , guest.getNationality());
		tl.addRow("Mobile Phone" , Long.toString(guest.getMobileNumber()));
		tl.print();

	}
			

	public void addName(Guest guest) {
		String name;
		do 
		{	
			System.out.print("Name: ");
			name = sc.nextLine();
			if (name.equals("")) 
				System.out.println("\nPlease enter a valid name.");
		} while (name.equals(""));
		guest.setName(name.toUpperCase());
		System.out.println("Success! Name recorded.");
	}

	public void addIDType(Guest guest) {
		String choice;
		boolean parser = false;
		do 
		{
			System.out.println("\nPlease select ID type: ");
			System.out.println("(1) Driving License");
			System.out.println("(2) Passport");
			do
			{
				choice = sc.nextLine();
				if (!isInteger(choice))
					System.out.println("Please enter a number.");
				else 
					parser = true;
			} while (parser == false);
			
			parser = false;
			
			if (Integer.parseInt(choice)== 1)
			{
				guest.setIdType(idType.DrivingLicense);
				parser = true;
			}
			else if (Integer.parseInt(choice) == 2)
			{
				guest.setIdType(idType.Passport);
				parser = true;
			}
			else
				System.out.println("Invalid input. Please try again.");
		} while(parser == false);

		do 
		{
			System.out.print("ID: ");
			choice = sc.nextLine().toUpperCase();
			
			if (choice.equals("")) 
				System.out.println("Please enter a valid guest ID");
		} while (choice.equals("") );
		guest.setguestID(choice);
				System.out.println("Success! Guest ID recorded");
	}


	public void addAddress(Guest guest) {
		String choice;
		do 
		{	
			System.out.print("\nAddress: ");
			choice = sc.nextLine();
			
			if (choice.equals("")) 
				System.out.println("\nPlease enter a valid address");
		} while (choice.equals("") );				
		guest.setAddress(choice);
		System.out.println("Success! Guest address recorded");
	}

	public void addGender(Guest guest) {
		String choice;
		do 
		{
			do
			{
				System.out.println("Gender");
				System.out.println("(1) Male");
				System.out.println("(2) Female");
				choice = sc.nextLine();
				if (!isInteger(choice))
					System.out.println("Please enter a number.");
			} while (!isInteger(choice));
			
			if (Integer.parseInt(choice) == 1)
			{
				guest.setGender("Male");
			}
			else if (Integer.parseInt(choice) == 2)
			{
				guest.setGender("Female");
			}
			else if (Integer.parseInt(choice) != 1 && Integer.parseInt(choice) != 2) 
			{
				System.out.println("Please enter a valid gender.");
			} 
		} while (Integer.parseInt(choice)!= 1 && Integer.parseInt(choice) != 2);
		System.out.println("Success! Gender recorded.");

	}

	public void addCountry(Guest guest) {
		String country;
		do 
		{
			System.out.print("\nCountry: ");
			country = sc.nextLine();
			if (country.equals("")) 
				System.out.println("Please enter a valid country");
		} while (country.equals(""));
		guest.setCountry(country);
		System.out.println("Success! Country recorded");
	}

	public void addNationality(Guest guest) { 
		String nationality;
		do 
		{
			System.out.print("\nNationality: ");
			nationality = sc.nextLine();
			if (nationality.equals("")) 
				System.out.println("Please enter a valid nationality.");
		} while (nationality.equals("") );
		guest.setNationality(nationality);
		System.out.println("Success! Nationality recorded.");
	}

	public void addCreditCardNumber(Guest guest) {
		String choice;
		boolean parser = false;
		do 
		{
			System.out.println("\nCredit Card Number: (Input 'NULL' if you would like to pay by Cash)");
			choice = sc.nextLine().toUpperCase();
			if (choice.equals("NULL")) {
				guest.setCreditCardNumber(0000000000000000);
				System.out.println("Credit Card not recorded.\n");
				parser = true;
			}
			else if (!isLong(choice))
				System.out.println("Please enter a valid Credit Card number.");
			else if (isLong(choice)) 
			{
				guest.setCreditCardNumber(Long.parseLong(choice));
				System.out.println("Success! Credit Card number recorded.");
				parser = true;
			}
		} while (parser == false);
	}

	public void addMobileNumber(Guest guest) {
		String mobileNumber;
		do 
		{
			System.out.print("\nMobile Number: ");
			mobileNumber = sc.nextLine();

			if (!isLong(mobileNumber))
				System.out.println("Please enter a valid Mobile Number.");
			else 
			{
				long m = Long.parseLong(mobileNumber);
				guest.setMobileNumber(m);
				System.out.println("Success! Mobile Number recorded.");
			}
		} while (!isLong(mobileNumber));
	}

	// @Override
	// public void removeDetails() {
	// 	// option not required.
	// 	return;
	// }


}



		
