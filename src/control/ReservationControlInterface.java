package control;

import entity.Reservation;
import enums.reservationStatus;

public interface ReservationControlInterface extends MainInterface<Reservation> {
    // public abstract void createDetails();
    // public abstract void updateDetails();
    
    public abstract void removeDetails();
    public abstract void checkIn();
    public abstract void noShow();
    public abstract void updateReservationStatus(Reservation reservation, reservationStatus updateResStatus);
    public abstract void printResDetailsByGuestID();
    public abstract void addReservationID(Reservation res);
	public abstract void addBookingType(Reservation res);
	public abstract void addBillingType(Reservation res);
	public abstract void addCheckInDate(Reservation res);
    public abstract void addCheckOutDate(Reservation res);
    public abstract void addAdults(Reservation res);
    public abstract void addChildren(Reservation res);
    public abstract void printDetails(Reservation res);

}