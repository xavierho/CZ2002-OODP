package entity;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")

public class Payment implements Serializable{
	private Reservation reservation;
	private double totalBill;
	private double tax = 0.07;
	private double roomCharges;
	private double roomServiceCharges;
	private double discount;
	private Date date;
	
	public Payment() {
		
	}
	
	public Payment(Reservation reservation, double totalBill, double tax, double roomCharges, double roomServiceCharges,
			double discount, Date date) {
		super();
		this.reservation = reservation;
		this.totalBill = totalBill;
		this.tax = tax;
		this.roomCharges = roomCharges;
		this.roomServiceCharges = roomServiceCharges;
		this.discount = discount;
		this.date = date;
	}


	public double getTotalBill() {
		return totalBill;
	}


	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}


	public double getTax() {
		return tax;
	}


	public void setTax(double tax) {
		this.tax = tax;
	}


	public double getRoomCharges() {
		return roomCharges;
	}


	public void setRoomCharges(double roomCharges) {
		this.roomCharges = roomCharges;
	}


	public double getRoomServiceCharges() {
		return roomServiceCharges;
	}


	public void setRoomServiceCharges(double roomServiceCharges) {
		this.roomServiceCharges = roomServiceCharges;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
