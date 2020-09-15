package control;

import entity.MenuItem;

public interface MenuItemControlInterface extends MainInterface<MenuItem> {

    public abstract void removeDetails();
	public abstract MenuItem searchMenuItem(int menuItemID);
	public abstract void addMenuItemName(MenuItem menuItem);
	public abstract void addMenuItemDescription(MenuItem menuItem);
	public abstract void menuItemPrice(MenuItem menuItem);
	public abstract void updateMenuOrder();
	public abstract void printDetails();

}