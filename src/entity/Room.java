package entity;

import java.io.Serializable;

import enums.bedType;
import enums.roomType;
import enums.status;
import enums.withView;

@SuppressWarnings("serial")

public class Room implements Serializable{
	
	private int roomFloor;
	private int roomNumber;
	private boolean wifiEnabled, smoking;
	private double roomRate;
	private roomType roomType; 
	private withView withView;
	private status status;
	private bedType bedType;
	
	public Room() {	
	}
	
	public Room(int roomFloor, int roomNumber,double roomRate, enums.roomType roomType,enums.bedType bedType,enums.status status,  enums.withView withView,boolean wifiEnabled, boolean smoking) {
		super();
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
		this.wifiEnabled = wifiEnabled;
		this.smoking = smoking;
		this.roomRate = roomRate;
		this.roomType = roomType;
		this.withView = withView;
		this.status = status;
		this.bedType = bedType;
	}
	public int getRoomFloor() {
		return roomFloor;
	}
	public void setRoomFloor(int roomFloor) {
		this.roomFloor = roomFloor;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public bedType getBedType() {
		return bedType;
	}
	public void setBedType(bedType bedType) {
		this.bedType = bedType;
	}
	public withView getWithView() {
		return withView;
	}
	public void setWithView(withView withView) {
		this.withView = withView;
	}
	public status getStatus() {
		return status;
	}
	public void setStatus(status status) {
		this.status = status;
	}
	
	public roomType getRoomType() {
		return roomType;
	}
	public void setRoomType(roomType roomType) {
		this.roomType = roomType;
	}
	
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}
	public boolean isSmoking() {
		return smoking;
	}
	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}
	public double getRoomRate() {
		return roomRate;
	}
	public void setRoomRate(double roomRate) {
		this.roomRate = roomRate;
	}
		
	
}
