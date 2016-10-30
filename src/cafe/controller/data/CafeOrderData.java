package cafe.controller.data;

public class CafeOrderData {
	int id;
	String staff;
	int tableId;

	public CafeOrderData() {
		super();
	}

	public CafeOrderData(String staff, int tableId) {
		super();
		this.staff = staff;
		this.tableId = tableId;
	}

	public CafeOrderData(int id, String staff, int tableId) {
		super();
		this.id = id;
		this.staff = staff;
		this.tableId = tableId;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

}
