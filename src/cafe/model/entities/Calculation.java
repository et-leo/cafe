package cafe.model.entities;

import javax.persistence.*;

@Entity
public class Calculation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CalculationId")
	private long id;
	@ManyToOne
	MenuItem menuItem;
	@ManyToOne
	Product product;
	int count;

	public Calculation() {
		super();
	}

	public Calculation(long id, MenuItem menuItem, Product product, int count) {
		super();
		this.id = id;
		this.menuItem = menuItem;
		this.product = product;
		this.count = count;
	}

	public Calculation(Product product, MenuItem menuItem, int count) {
		super();
		this.product = product;
		this.menuItem = menuItem;
		this.count = count;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
