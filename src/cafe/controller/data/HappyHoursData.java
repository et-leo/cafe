package cafe.controller.data;

import java.time.LocalDateTime;

public class HappyHoursData {
	int id;
	LocalDateTime discountFrom;
	LocalDateTime discountTo;
	int discountPercent;
	MenuItemData menuItemData;

	public HappyHoursData(LocalDateTime discountFrom, LocalDateTime discountTo, int discountPercent,
			MenuItemData menuItemData) {
		super();
		this.discountFrom = discountFrom;
		this.discountTo = discountTo;
		this.discountPercent = discountPercent;
		this.menuItemData = menuItemData;
	}

	public HappyHoursData(int id, LocalDateTime discountFrom, LocalDateTime discountTo, int discountPercent,
			MenuItemData menuItemData) {
		super();
		this.id = id;
		this.discountFrom = discountFrom;
		this.discountTo = discountTo;
		this.discountPercent = discountPercent;
		this.menuItemData = menuItemData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDiscountFrom() {
		return discountFrom;
	}

	public void setDiscountFrom(LocalDateTime discountFrom) {
		this.discountFrom = discountFrom;
	}

	public LocalDateTime getDiscountTo() {
		return discountTo;
	}

	public void setDiscountTo(LocalDateTime discountTo) {
		this.discountTo = discountTo;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public MenuItemData getMenuItemData() {
		return menuItemData;
	}

	public void setMenuItemData(MenuItemData menuItemData) {
		this.menuItemData = menuItemData;
	}

}
