package cz.vutbr.feec.iot.security;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cz.vutbr.feec.iot.dto.user.UserAuthenticateDTO;
import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.facade.iface.UserFacade;

/**
 * @author Pavel Šeda
 *
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

  private UserFacade userFacade;

  @Inject
  public AuthenticationProviderImpl(UserFacade userFacade) {
    this.userFacade = userFacade;
  }

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {

    String email = auth.getName();
    String pwd = (String) auth.getCredentials();

    UserDTO userDTO = userFacade.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

    UserAuthenticateDTO user = new UserAuthenticateDTO();
    user.setEmail(email);
    user.setPasswordHash(userDTO.getPasswordHash());
    if (!userFacade.authenticate(user, pwd)) {
      throw new BadCredentialsException("Provide valid email or password");
    }

    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(getUserRoles(userDTO));
    return new UsernamePasswordAuthenticationToken(email, pwd, authorities);
  }

  public String[] getUserRoles(UserDTO userDTO) {
    String[] roles = new String[userDTO.getRoles().size()];
    for (int i = 0; i < userDTO.getRoles().size(); i++) {
      roles[i] = userDTO.getRoles().get(i).getRole();
    }
    return roles;
  }

  public static String getLoggedUserEmail() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getName();
  }

  @Override
  public boolean supports(Class<?> auth) {
    return true;
  }

}
