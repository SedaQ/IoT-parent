package cz.vutbr.feec.iot.facade.iface;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.types.Predicate;

import cz.vutbr.feec.iot.dto.user.UserAuthenticateDTO;
import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.dto.user.UserRegistrationDTO;

/**
 * @author Pavel Šeda
 *
 */
public interface UserFacade {

  /**
   * Finds user by ID
   * 
   * @param id ID to look for
   * @return Optional with UserDTO if found, else empty
   */
  Optional<UserDTO> findById(Long id);

  /**
   * Returns all user in language school
   * 
   * @return List of user which are in language school
   */
  List<UserDTO> getAllUsers();

  /**
   * Attempts to find a user by email address
   * 
   * @param email email to search for
   * @return Optional instance, with UserEntity if found, empty otherwise
   */
  Optional<UserDTO> findByEmail(String email);

  /**
   * updates given user
   * 
   * @param c user that has to be updated
   * @return updated user
   */
  Optional<UserDTO> update(UserDTO userDTO);

  /**
   * removes given user
   * 
   * @param c user that has to be removed
   */
  void remove(UserDTO userDTO);

  /**
   * Register the given user with the given unencrypted password.
   */
  Optional<UserDTO> registerUser(UserRegistrationDTO userDTO, String unencryptedPassword);

  /**
   * Try to authenticate a user. Return true only if the hashed password matches the records.
   */
  boolean authenticate(UserAuthenticateDTO userDTO, String password);

  /**
   * Check if the given user is admin.
   */
  boolean isAdmin(UserDTO userDTO);

  /**
   * Returns all user in language school
   * 
   * @return List of user which are in language school
   */
  List<UserDTO> getAllUsersWithParams(Predicate predicate, Pageable pageRequest);

}
