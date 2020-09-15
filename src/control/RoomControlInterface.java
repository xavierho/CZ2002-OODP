package control;

import entity.Room;

public interface RoomControlInterface extends MainInterface<Room> {
 
    // public abstract void removeDetails();
    
	public abstract Room addRoom(Room room);
    public abstract void addRoomRate(Room room);
    public abstract void addRoomType(Room room);
	public abstract void addBedType(Room room);
	public abstract void addRoomView(Room room);
	public abstract void addRoomWifi(Room room);
	public abstract void addRoomSmoking(Room room);
	public abstract void updateRoomStatus(Room room, int type);
	public abstract void updateMaintenance();
	public abstract void printRoomStatus();
	public abstract void roomOccupancyReport();
    public abstract Room checkUnitExists(int floor, int unit);
    public abstract void printDetails();
    public abstract void sortRooms();
    public abstract void printRooms(boolean available);






}