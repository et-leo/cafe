package cafe.controller.data;

public class CalculationData {
	int id;
	ProductData product;
	MenuItemData menuItem;
	int count;

	public CalculationData(ProductData product, MenuItemData menuItem, int count) {
		super();
		this.product = product;
		this.menuItem = menuItem;
		this.count = count;
	}

	public CalculationData(int id, ProductData product, MenuItemData menuItem, int count) {
		super();
		this.id = id;
		this.product = product;
		this.menuItem = menuItem;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductData getProduct() {
		return product;
	}

	public void setProduct(ProductData product) {
		this.product = product;
	}

	public MenuItemData getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItemData menuItem) {
		this.menuItem = menuItem;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
