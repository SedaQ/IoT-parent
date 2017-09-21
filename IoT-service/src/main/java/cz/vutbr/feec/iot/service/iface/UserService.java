package cz.vutbr.feec.iot.service.iface;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.types.Predicate;

import cz.vutbr.feec.iot.entity.UserEntity;

/**
 * @author Pavel Šeda
 *
 */
public interface UserService {

  /**
   * Finds user by id
   * 
   * @param id ID to look for
   * @return Optional with UserEntity if found, else empty
   */
  Optional<UserEntity> findById(Long id);

  /**
   * Returns all user in language school
   * 
   * @return List of user which are in language school
   */
  List<UserEntity> findAll();

  /**
   * Returns all user in language school
   * 
   * @return List of user which are in language school
   */
  List<UserEntity> findAll(Predicate predicate, Pageable pageRequest);

  /**
   * Attempts to find a user by email address
   * 
   * @param email email to search for
   * @return Optional instance, with UserEntity if found, empty otherwise
   */
  Optional<UserEntity> findByEmail(String email);

  /**
   * updates given user
   * 
   * @param c user that has to be updated
   * @return updated user
   */
  Optional<UserEntity> update(UserEntity userEntity);

  /**
   * removes given user
   * 
   * @param c user that has to be removed
   */
  void remove(UserEntity userEntity);

  /**
   * Register the given user with the given unencrypted password.
   */
  Optional<UserEntity> registerUser(UserEntity userEntity, String unencryptedPassword);

  /**
   * Try to authenticate a user. Return true only if the hashed password matches the records.
   */
  boolean authenticate(UserEntity userEntity, String password);

  /**
   * Check if the given user is admin.
   */
  boolean isAdmin(UserEntity userEntity);

}
