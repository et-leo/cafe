package cafe.model.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class HappyHours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DiscountId")
	private long id;
	LocalDateTime discountFrom;
	LocalDateTime discountTo;
	int discountPercent;
	@ManyToOne 
	MenuItem menuItem;

	public HappyHours() {
		super();
	}

	public HappyHours(LocalDateTime discountFrom, LocalDateTime discountTo, int discountPercent, MenuItem menuItem) {
		super();
		this.discountFrom = discountFrom;
		this.discountTo = discountTo;
		this.discountPercent = discountPercent;
		this.menuItem = menuItem;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

}
