package cafe.controller.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.*;

@Configuration
public class SecureConfiguration implements UserDetailsService {
	@Autowired
	CredConfig credentials;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		String password = credentials.credMap.get(name);
		if (password == null) {
			new UsernameNotFoundException(name + " user not found");
		}
		return new User(name, password, AuthorityUtils.createAuthorityList(credentials.authMap.get(name)));
	}

	// iOrmMethods cafe;
	// @Override
	// public UserDetails loadUserByUsername(String name) throws
	// UsernameNotFoundException {
	//
	// Staff staff = cafe.getStaffById(name);
	// String password = staff.getPassword();
	// if (password == null) {
	// new UsernameNotFoundException(name + " user not found");
	// }
	// return new User(staff.getName(), staff.getPassword(),
	// AuthorityUtils.createAuthorityList(staff.getRole().getRole()));
	// }
}
