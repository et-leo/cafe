package cafe.model.entities;

import javax.persistence.*;

@Entity
public class ItemQuantity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ItemsQuantityId")
	private long id;
	@ManyToOne
	private MenuItem menuItem;
	@ManyToOne
	private CafeOrder order;
	private int quantity;
	private double price;
	private double discountAmount;
	private double finalCost;

	public ItemQuantity() {
		super();
	}

	public ItemQuantity(MenuItem item, int quantity, CafeOrder order) {
		super();
		this.order = order;
		this.menuItem = item;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getFinalCost() {
		return finalCost;
	}

	public void setFinalCost(double finalCost) {
		this.finalCost = finalCost;
	}

	public CafeOrder getOrder() {
		return order;
	}

	public void setOrder(CafeOrder order) {
		this.order = order;
	}

	public MenuItem getItem() {
		return menuItem;
	}

	public void setItem(MenuItem item) {
		this.menuItem = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
