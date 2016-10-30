package cafe.model.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

@Entity
public class CafeOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private long id;

	@ManyToOne
	private Staff staff;
	@ManyToOne
	private OrderStatus orderStatus;
	@ManyToOne
	private TablePlace tablePlace;
	@ManyToOne
	private Client client;
	@OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
	private Set<ItemQuantity> itemsQuantity;

	LocalDateTime date;
	int nGuests;
	double orderCheck;
	boolean isCash;

	public CafeOrder() {
		super();
	}

	public CafeOrder(Staff staff, TablePlace tablePlace) {
		super();
		this.staff = staff;
		this.tablePlace = tablePlace;
	}

	public CafeOrder(long id, Staff staff, OrderStatus orderStatus, TablePlace tablePlace, Client client,
			Set<ItemQuantity> itemsQuantity, LocalDateTime date, int nGuests, double orderCheck, boolean isCash) {
		super();
		this.id = id;
		this.staff = staff;
		this.orderStatus = orderStatus;
		this.tablePlace = tablePlace;
		this.client = client;
		this.itemsQuantity = itemsQuantity;
		this.date = date;
		this.nGuests = nGuests;
		this.orderCheck = orderCheck;
		this.isCash = isCash;
	}

	public CafeOrder(Staff staff, OrderStatus orderStatus, TablePlace tablePlace, Client client, LocalDateTime date,
			int nGuests, double orderCheck, boolean isCash) {
		super();
		this.staff = staff;
		this.orderStatus = orderStatus;
		this.tablePlace = tablePlace;
		this.client = client;
		this.date = date;
		this.nGuests = nGuests;
		this.orderCheck = orderCheck;
		this.isCash = isCash;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public TablePlace getTablePlace() {
		return tablePlace;
	}

	public void setTablePlace(TablePlace tablePlace) {
		this.tablePlace = tablePlace;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<ItemQuantity> getItemsQuantity() {
		return itemsQuantity;
	}

	public void setItemsQuantity(Set<ItemQuantity> itemsQuantity) {
		this.itemsQuantity = itemsQuantity;
	}

	public LocalDateTime getDateTime() {
		return date;
	}

	public void setDateTime(LocalDateTime date) {
		this.date = date;
	}

	public int getnGuests() {
		return nGuests;
	}

	public void setnGuests(int nGuests) {
		this.nGuests = nGuests;
	}

	public double getOrderCheck() {
		return orderCheck;
	}

	public void setOrderCheck(double orderCheck) {
		this.orderCheck = orderCheck;
	}

	public boolean isCash() {
		return isCash;
	}

	public void setCash(boolean isCash) {
		this.isCash = isCash;
	}

}
