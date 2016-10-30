package cafe.model.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Staff {

	@Id
	private String name;
	private String password;
	@ManyToOne
	private Role role;
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
	private Set<CafeOrder> orders;

	public Staff(String name, String password, Role role) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public Staff() {
		super();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
