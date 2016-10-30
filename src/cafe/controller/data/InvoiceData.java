package cafe.controller.data;

import java.time.LocalDate;

public class InvoiceData {
	long id;
	SupplierData supplier;
	ProductData product;
	double price;
	int amount;
	LocalDate date;
	boolean isPaid;

	public InvoiceData(SupplierData supplier, ProductData product, double price, int amount, LocalDate date,
			boolean isPaid) {
		super();
		this.supplier = supplier;
		this.product = product;
		this.price = price;
		this.amount = amount;
		this.date = date;
		this.isPaid = isPaid;
	}

	public InvoiceData(long id, SupplierData supplier, ProductData product, double price, int amount, LocalDate date,
			boolean isPaid) {
		super();
		this.id = id;
		this.supplier = supplier;
		this.product = product;
		this.price = price;
		this.amount = amount;
		this.date = date;
		this.isPaid = isPaid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public SupplierData getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierData supplier) {
		this.supplier = supplier;
	}

	public ProductData getProduct() {
		return product;
	}

	public void setProduct(ProductData product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public InvoiceData() {
		super();
	}

}
