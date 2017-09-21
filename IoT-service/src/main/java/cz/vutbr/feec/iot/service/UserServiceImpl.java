package cz.vutbr.feec.iot.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.mysema.query.types.Predicate;

import cz.vutbr.feec.iot.dao.UserRepository;
import cz.vutbr.feec.iot.entity.UserEntity;
import cz.vutbr.feec.iot.exception.EmailExistsException;
import cz.vutbr.feec.iot.exception.ServiceLayerException;
import cz.vutbr.feec.iot.security.UserPasswordEncryption;
import cz.vutbr.feec.iot.service.iface.UserService;

/**
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

  private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

  private UserRepository userRepository;
  private UserPasswordEncryption userPasswordEncryption;

  @Inject
  public UserServiceImpl(UserRepository userRepository,
      UserPasswordEncryption userPasswordEncryption) {
    this.userRepository = userRepository;
    this.userPasswordEncryption = userPasswordEncryption;
  }

  /**
   * {@see UserService#findById(Long)}
   */
  @Override
  public Optional<UserEntity> findById(Long id) {
    if (id == null)
      throw new NullPointerException("findById method must have not null id argument to search");
    try {
      UserEntity userEntity = userRepository.findOne(id);
      if (userEntity == null) {
        System.out.println("Vraci OPTIONAL EMPTY, protoze neexistuje");
        return Optional.empty();
      } else {
        return Optional.of(userEntity);
      }
    } catch (ServiceLayerException ex) { // | NoSuchElementException
      logger.error("findById on service layer: " + ex);
      throw new ServiceLayerException("findById did not find any result: " + ex);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<UserEntity> findAll() {
    try {
      return userRepository.findAll();
    } catch (ServiceLayerException ex) { // | NoSuchElementException
      logger.error("findAll on service layer: " + ex);
      throw new ServiceLayerException("findAll method did not find any result: " + ex);
    }
  }

  @Override
  public List<UserEntity> findAll(Predicate predicate, Pageable pageRequest) {
    try {
      if (pageRequest == null) {
        pageRequest = new PageRequest(0, 100);
      }
      List<UserEntity> users = Lists.newArrayList();
      Page<UserEntity> usersIterable = userRepository.findAll(predicate, pageRequest);
      usersIterable.forEach(users::add);
      return users;
    } catch (ServiceLayerException ex) { // | NoSuchElementException
      logger.error("findAll on service layer: " + ex);
      throw new ServiceLayerException("findAll method did not find any result: " + ex);
    }
  }


  /**
   * {@link UserService#findByEmail(String)}
   */
  @Override
  public Optional<UserEntity> findByEmail(String email) {
    if (email == null)
      throw new NullPointerException(
          "findByEmail method must have not null email argument to search");
    else if (email.isEmpty())
      throw new IllegalArgumentException(
          "FindByEmail method must have not empty email argument to search");
    try {
      return Optional.ofNullable(userRepository.findByEmail(email));
    } catch (ServiceLayerException ex) { // | NoSuchElementException
      logger.error("findByEmail on service layer: " + ex);
      throw new ServiceLayerException("findByEmail method did not find any result: " + ex);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<UserEntity> update(UserEntity userEntity) {
    if (userEntity == null)
      throw new NullPointerException(
          "update method must have not null userEntity argument to update this entity");
    try {
      return Optional.ofNullable(userRepository.save(userEntity));
    } catch (ServiceLayerException ex) { // | NoSuchElementException
      logger.error("update on service layer: " + ex);
      throw new ServiceLayerException("update method occurs some error" + ex);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove(UserEntity userEntity) {
    if (userEntity == null)
      throw new NullPointerException(
          "remove method must have not null userEntity argument to remove this entity");
    try {
      userRepository.delete(userEntity);
    } catch (ServiceLayerException ex) { // | NoSuchElementException
      logger.error("remove on service layer: " + ex);
      throw new ServiceLayerException("update method occurs some error" + ex);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<UserEntity> registerUser(UserEntity userEntity, String unencryptedPassword) {
    if (userEntity == null)
      throw new IllegalArgumentException("UserEntity userEntity parameter is null");
    if (unencryptedPassword == null)
      throw new IllegalArgumentException("String unencryptedPassword parameter is null");
    if (emailExists(userEntity.getEmail())) {
      throw new EmailExistsException(
          "There is an account with that email address:" + userEntity.getEmail());
    }
    try {
      userEntity.setPasswordHash(userPasswordEncryption.createHash(unencryptedPassword));
      return Optional.ofNullable(userRepository.save(userEntity));
    } catch (RuntimeException ex) {
      logger.error("registerUser on service layer: " + ex);
      throw new ServiceLayerException("Problem with registering UserEntity, see inner exception.",
          ex);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean authenticate(UserEntity userEntity, String password) {
    if (userEntity == null)
      throw new IllegalArgumentException("UserEntity userEntity parameter is null");
    if (password == null)
      throw new IllegalArgumentException("String password parameter is null");
    if (userEntity.getPasswordHash() == null)
      throw new IllegalArgumentException("UserEntity should have stored password");
    try {
      return userPasswordEncryption.validatePassword(password, userEntity.getPasswordHash());
    } catch (RuntimeException ex) {
      logger.error("authenticate on service layer: " + ex);
      throw new ServiceLayerException(
          "Problem with authenticating UserEntity, see inner exception.", ex);
    }
  }

  private boolean emailExists(String email) {
    UserEntity user = userRepository.findByEmail(email);
    if (user != null)
      return true;
    else
      return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAdmin(UserEntity u) {
    // TODO Auto-generated method stub
    return false;
  }

}
