package entity;

import java.io.Serializable;
import java.util.Date;
import enums.orderStatus;

@SuppressWarnings("serial")

public class RoomService implements Serializable{
	private int serviceID;
	private String remarks;
	private Date orderDate;
	private orderStatus orderStatus;
	private MenuItem item;
	private Guest guest;
	private Room room;
	
	public RoomService() {
		
	}
	
	public RoomService(int serviceID, String remarks, Date orderDate, enums.orderStatus orderStatus, MenuItem item,
			Guest guest, Room room) {
		super();
		this.serviceID = serviceID;
		this.remarks = remarks;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.item = item;
		this.guest = guest;
		this.room = room;
	}
	
	public MenuItem getItem() {
		return item;
	}
	public void setItem(MenuItem item) {
		this.item = item;
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public orderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(orderStatus orderStatus) {
		this.orderStatus = orderStatus;
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
}
