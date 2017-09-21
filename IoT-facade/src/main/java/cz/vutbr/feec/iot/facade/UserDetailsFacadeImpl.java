package cz.vutbr.feec.iot.facade;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.vutbr.feec.iot.service.UserDetailsServiceImpl;

/**
 * @author Pavel Šeda
 *
 */
@Service("myUserDetailsServiceImpl")
@Transactional(readOnly = true)
public class UserDetailsFacadeImpl implements UserDetailsService {

	private static Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

	private UserDetailsServiceImpl userService;

	@Inject
	public UserDetailsFacadeImpl(UserDetailsServiceImpl userService) {
		this.userService = userService;
	}

	/*
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername: " + username);
		return userService.loadUserByUsername(username);
	}

}