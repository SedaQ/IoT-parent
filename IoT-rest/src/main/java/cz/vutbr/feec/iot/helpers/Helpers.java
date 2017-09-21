package cz.vutbr.feec.iot.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Pavel Šeda
 *
 */
public class Helpers {

  /**
   * Check if user has specific role
   * 
   * @param role Specific role to check
   * @return true if user has specific role
   */
  public static boolean hasRole(String role) {
    // get security context from thread local
    SecurityContext context = SecurityContextHolder.getContext();
    if (context == null)
      return false;
    Authentication authentication = context.getAuthentication();
    if (authentication == null)
      return false;
    for (GrantedAuthority auth : authentication.getAuthorities()) {
      if (role.equals(auth.getAuthority()))
        return true;
    }
    return false;
  }

}
