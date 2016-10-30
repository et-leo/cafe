package cafe.model.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Supplier {
	@Id
	int id;
	String info;
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	Set<Invoice> invoices;

	public Supplier(int id, String info) {
		super();
		this.id = id;
		this.info = info;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", info=" + info + "]";
	}

	public Supplier() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}
