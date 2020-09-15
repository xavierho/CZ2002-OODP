package control;

import java.util.*;

import entity.MenuItem;

public class MenuItemControl extends CoreUtilities implements MenuItemControlInterface {
	
	private static ArrayList<MenuItem> menuitemList;
	
	public MenuItemControl() {
		menuitemList = new ArrayList<MenuItem>();
	}
	Scanner sc = new Scanner(System.in);
	
	// method to create new menu item
	public void createDetails() {
		System.out.println("Creating New Item ======================");
		
		menuitemList = Database.getMenuItem();
		MenuItem menuItem = new MenuItem();
		
		menuItem.setItemID(menuitemList.size()+1);
		addMenuItemName(menuItem);
		addMenuItemDescription(menuItem);
		menuItemPrice(menuItem);
		
		String choice;
		do {
			do {
				System.out.println("Confirm Menu Item?");
				System.out.println("(1) Yes   (2) No ");
				System.out.println("Input: ");

				choice = sc.nextLine();
				if (!isInteger(choice))
					System.out.println("Please enter a number.");
			} while (!isInteger(choice));
			if(Integer.parseInt(choice) == 1) {
				Database.addMenuItem(menuItem);
				System.out.println("Success! Item created.\n");
			}
			else if (Integer.parseInt(choice) == 2){
				System.out.println("Item Not Saved");
			}
			else if (Integer.parseInt(choice) != 1 && Integer.parseInt(choice) != 2) 
			{
				System.out.println("Please enter a valid choice.");
			} 
		}while (Integer.parseInt(choice)!= 1 && Integer.parseInt(choice) != 2);
		
	}
	
	//method to update menu item
	@Override
	public void updateDetails() {
		System.out.println("Updating Item =====================");
		
		MenuItem menuItem = getDetails();
		
		System.out.println("What would you like to update about ");
		System.out.println("(1) Update Item Name");
		System.out.println("(2) Update Item Price");
		System.out.println("(3) Update Item Description");
		System.out.print("Input: ");

		
		do {
			System.out.println("-----------------------------------------------");
			int choice = intOptionParser(0, 3);
			
			switch(choice) {
			case 1:
				addMenuItemName(menuItem);
				break;
			case 2:
				menuItemPrice(menuItem);
				break;
			case 3:
				addMenuItemDescription(menuItem);
				break;
			default:
				break;
			}
		}while(continuePrompt("updating", menuItem.getItemName()));
		
		Database.saveMenuItem(menuitemList);
		
		System.out.println("Success! Menu Item updated!");
	}
	
	public void removeDetails() {
		System.out.println("Removing Item ===================");
		
		MenuItem menuItem = getDetails();
		
		menuitemList = Database.getMenuItem();
		
		String choice;
		do {
			do {
				System.out.println("Confirm Menu Item?");   
				System.out.println("(1) Yes   (2) No ");
				System.out.print("Input: ");

				choice = sc.nextLine();
				if (!isInteger(choice))
					System.out.println("Please enter a number.");
			} while (!isInteger(choice));
			if(Integer.parseInt(choice) == 1) {
				for(int i=0; i<menuitemList.size();i++) {
					MenuItem removeMenuItem = (MenuItem) menuitemList.get(i);
					if(menuItem.getItemID() == (removeMenuItem.getItemID())) {
						menuitemList.remove(i);
						break;
					}
				}
			}
			else if (Integer.parseInt(choice) == 2){
				System.out.println("Item Not Removed");
			}
			else if (Integer.parseInt(choice) != 1 && Integer.parseInt(choice) != 2) 
			{
				System.out.println("Please enter a valid choice.\n");
			} 
		}while (Integer.parseInt(choice)!= 1 && Integer.parseInt(choice) != 2);
		updateMenuOrder();
		Database.saveMenuItem(menuitemList);
		System.out.println("Success! Menu Item Removed.\n");
	}
	
	public void printDetails() {
		menuitemList = Database.getMenuItem();
		TableList tl = new TableList(4, "Item ID", "Item Name", "Description", "S$ Price").withUnicode(true);
		for (int j = 0; j < menuitemList.size(); j++) {
			MenuItem curm = (MenuItem) menuitemList.get(j);
			tl.addRow(Integer.toString(curm.getItemID()), curm.getItemName(), curm.getItemDescription(), Double.toString(curm.getItemPrice()));
		}
		tl.print();		
	}
	
	public MenuItem searchMenuItem(int menuItemID) {
		menuitemList = Database.getMenuItem();
		
		for(int i=0;i<menuitemList.size();i++) {
			MenuItem searchMenuItem = menuitemList.get(i);
			
			if(menuItemID == searchMenuItem.getItemID()) {
				return searchMenuItem;
			}
		}
		return null;
	}
	
	
	
	public void addMenuItemName(MenuItem menuItem) {
		String itemName = null;
		do {
			System.out.println("Enter Item Name: ");
			try {
				itemName = sc.nextLine();
				if(itemName.equals(" ")) {
					System.out.println("You have not entered any name!");
				}
				else {
					menuItem.setItemName(itemName);
				}
			}catch(InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
			}
		}while(itemName.equals(" "));
	}
	
	public void addMenuItemDescription(MenuItem menuItem) {
		String itemDescription = null;
		do {
			System.out.println("Enter Item Description: ");
			try {
				itemDescription = sc.nextLine();
				if(itemDescription.equals(" ")) {
					System.out.println("You have not entered any description!");
				}
				else {
					menuItem.setItemDescription(itemDescription);
				}
			}catch(InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.\n");
			}
		}while(itemDescription.equals(" "));
	}
	
	public void menuItemPrice(MenuItem menuItem) {
		String itemPrice;
		do {
			System.out.println("Enter Item Price: ");
			itemPrice = sc.nextLine();
			if(!isDouble(itemPrice) || Double.parseDouble(itemPrice) < 0)
				System.out.println("Please enter a valid price. ");
			else {
				double p = Double.parseDouble(itemPrice);
				menuItem.setItemPrice(p);
			}
		}while(!isDouble(itemPrice) || Double.parseDouble(itemPrice) < 0);
	}

	public MenuItem getDetails() {
		MenuItem menuItem = new MenuItem();
		String itemID;
		
		do {
			System.out.println("Enter Item ID: ");
			itemID = sc.nextLine();
			if(!isInteger(itemID)) {
				System.out.println("Please enter a valid Item ID");
			}
			else {
				int id = Integer.parseInt(itemID);
				menuItem = searchMenuItem(id);
				
				if (menuItem == null)
					System.out.println("Item does not exist! Please enter a valid Item ID.\n");
			}
		}while(!isInteger(itemID) || menuItem == null);
		
		return menuItem;
	}




	public void updateMenuOrder() {
		menuitemList = Database.getMenuItem();
		for(int i=0; i<menuitemList.size();i++) {
			MenuItem updateMenuItem = (MenuItem) menuitemList.get(i);
			updateMenuItem.setItemID(i+1);
		}
	}
}
