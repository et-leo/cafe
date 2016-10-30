package cafe.controller.authorization;

import java.util.Map;


public class CredConfig {
	// public Staff staff;
	//
	// public CredConfig() {
	// super();
	// }
	//
	// public CredConfig(Staff staff) {
	// super();
	// this.staff = staff;
	// }
	//
	// public Staff getStaff() {
	// return staff;
	// }
	//
	// public void setStaff(Staff staff) {
	// this.staff = staff;
	// }
	public Map<String, String> credMap;
	public Map<String, String> authMap;

	public CredConfig() {
		super();
	}

	public Map<String, String> getAuthMap() {
		return authMap;
	}

	public void setAuthMap(Map<String, String> authMap) {
		this.authMap = authMap;
	}

	public Map<String, String> getCredMap() {
		return credMap;
	}

	public void setCredMap(Map<String, String> credMap) {
		this.credMap = credMap;
	}
}
