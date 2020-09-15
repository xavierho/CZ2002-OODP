package entity;

import java.io.Serializable;
import java.util.*;


//import ooproject.Guest;
//import ooproject.Room;
import enums.bookingType;
import enums.reservationStatus;
import enums.billingType;

@SuppressWarnings("serial")
public class Reservation implements Serializable { 
	
	private String reservationID;
	private Guest guest; //link guest name from guest to reservation
	private Room room; // changed these to the direct class instead of just the IDs.
	private billingType billingType;
	private Date checkInDate;
	private Date checkOutDate;
	private int numOfAdults;
	private int numOfChildren;
	private reservationStatus reservationStatus;
	private bookingType bookingType; //set default to preBooking
	
	
	public Reservation() {
		
	}
	
	public Reservation(String reservationID, Guest guest, Room room, enums.billingType billingType, Date checkInDate,
			Date checkOutDate, int numOfAdults, int numOfChildren, enums.reservationStatus reservationStatus,
			enums.bookingType bookingType) {
		super();
		this.reservationID = reservationID;
		this.guest = guest;
		this.room = room;
		this.billingType = billingType;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numOfAdults = numOfAdults;
		this.numOfChildren = numOfChildren;
		this.reservationStatus = reservationStatus;
		this.bookingType = bookingType;
	}

	public String getReservationID() {
		return reservationID;
	}
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}

	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public billingType getBillingType() {
		return billingType;
	}

	public void setBillingType(billingType billingType) {
		this.billingType = billingType;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNumOfAdults() {
		return numOfAdults;
	}
	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
	}

	public int getNumOfChildren() {
		return numOfChildren;
	}
	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}
	
	public reservationStatus getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(reservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	
	public bookingType getBookingType() {
		return bookingType;
	}
	public void setBookingType(bookingType bookingType) {
		this.bookingType = bookingType;
	}
}
