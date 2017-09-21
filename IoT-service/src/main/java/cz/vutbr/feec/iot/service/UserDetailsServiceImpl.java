package cz.vutbr.feec.iot.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cz.vutbr.feec.iot.dao.UserRepository;
import cz.vutbr.feec.iot.entity.RoleEntity;
import cz.vutbr.feec.iot.entity.UserEntity;

/**
 * @author Pavel Šeda
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

	private UserRepository userRepository;

	@Inject
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userRepository.findByEmail(username);
		if (user == null) {
			logger.debug("user not found with the provided username");
			throw new UsernameNotFoundException(username);
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		// email and password must not be null!
		return new User(user.getEmail(), user.getPasswordHash(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, getAuthorities(user));
	}

	private Set<GrantedAuthority> getAuthorities(UserEntity user) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (RoleEntity role : user.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
			authorities.add(grantedAuthority);
		}
		logger.debug("user authorities are " + authorities.toString());
		return authorities;
	}

}