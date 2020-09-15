package control;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoreUtilities {

	// parses the integer and checks if it is valid
	public static int intOptionParser(int min, int max) {
        System.out.print("Input: ");
		int choice = 0;
		do {
			try {
				Scanner sc = new Scanner(System.in);
				choice = sc.nextInt();
					if (choice < min || choice > max) {
						throw new Exception();
					}
			} catch (InputMismatchException e) {
				System.out.print("Input was not an integer. Please re-enter: ");
			} catch (Exception e) {
				System.out.println("Input out of range. Please try again.");
				System.out.print("Re-enter choice: ");
			}
		} while (choice < min || choice > max);


		return choice;
    }
    
    // check if user wants to continue with the current configuration
	public static boolean continuePrompt(String option, String control) {
		System.out.println("Would you like to continue " + option + " " + control+  " details?");
		System.out.println("(Y) Continue (Any) Cancel");
		try {
		Scanner sc = new Scanner(System.in);
		char choice = sc.nextLine().toLowerCase().charAt(0);
		if (choice== 'y') {
			return true;
		}
	} catch (StringIndexOutOfBoundsException e) {
	}	
	return false;
    }
    
    public static boolean isLong(String strNum)
    {
        boolean isValidLong = false;
        try
    {
       Long.parseLong(strNum);   
       isValidLong = true;
    }
    catch (NumberFormatException ex)
    {
        // s is not a Long 
    }
    return isValidLong;
    }

	
	public static boolean isInteger(String strNum) {
		boolean isValidInteger = false;
		try
		{
		   Integer.parseInt(strNum);   
		   isValidInteger = true;
		}
		catch (NumberFormatException ex)
		{
			// s is not an integer 
		}
		return isValidInteger;
	}

	public static boolean isDouble(String strNum) {
		boolean isValidDouble = false;
		try
		{
		   Double.parseDouble(strNum);   
		   isValidDouble = true;
		}
		catch (NumberFormatException ex)
		{
			// s is not a Double 
		}
		return isValidDouble;
	}

}