package control;

import entity.Guest;

public interface GuestControlInterface extends MainInterface<Guest> {
    // public abstract void createDetails(); 
    // public abstract void updateDetails();
    // public abstract void getDetails();
    // public abstract void printDetails();
    
    public abstract void printDetails(Guest guest);
    public abstract void searchGuestByKeyword();
    public abstract Guest searchGuestById(Guest guest);

    public abstract void addName(Guest guest);
    public abstract void addGender(Guest guest);
    public abstract void addIDType(Guest guest);
    public abstract void addNationality(Guest guest);
    public abstract void addAddress(Guest guest);
    public abstract void addCountry(Guest guest);
    public abstract void addMobileNumber(Guest guest);
    public abstract void addCreditCardNumber(Guest guest);


}