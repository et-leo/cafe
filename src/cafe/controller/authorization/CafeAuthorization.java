package cafe.controller.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CafeAuthorization extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().antMatchers(HttpMethod.GET).hasRole("admin").and().authorizeRequests()
		// .antMatchers("/getSupplier").authenticated().and().authorizeRequests().anyRequest().permitAll().and()
		// .httpBasic().and().csrf().disable();
	}
}
