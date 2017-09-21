package cz.vutbr.feec.iot.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.inject.Inject;

import cz.vutbr.feec.iot.dto.user.UserAuthenticateDTO;
import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.dto.user.UserRegistrationDTO;
import cz.vutbr.feec.iot.entity.UserEntity;
import cz.vutbr.feec.iot.exceptions.FacadeLayerException;
import cz.vutbr.feec.iot.facade.iface.UserFacade;
import cz.vutbr.feec.iot.mapping.BeanMapping;
import cz.vutbr.feec.iot.service.iface.UserService;

/**
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

  private static Logger logger = LogManager.getLogger(UserFacadeImpl.class);

  private UserService userService;
  private BeanMapping beanMapping;

  @Inject
  public UserFacadeImpl(UserService userService, BeanMapping beanMapping) {
    this.userService = userService;
    this.beanMapping = beanMapping;
  }

  /*
   * @see com.cz.better.lawyer.facade.iface.UserFacade#findById(java.lang.Long)
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<UserDTO> findById(Long id) {
    if (id == null)
      throw new NullPointerException("Id parameter is null");
    if (id < 0)
      throw new IllegalArgumentException("Id cannot be lower than zero.");
    try {
      Optional<UserEntity> userEntity = userService.findById(id);
      return userEntity.isPresent()
          ? Optional.ofNullable(beanMapping.mapTo(userEntity.get(), UserDTO.class))
          : Optional.empty();
    } catch (FacadeLayerException | NoSuchElementException ex) {
      logger.warn("findById method invokes exception: " + ex);
      return Optional.empty();
    }

  }

  /*
   * @see com.cz.better.lawyer.facade.iface.UserFacade#getAllUsers()
   */
  @Override
  @Transactional(readOnly = true)
  public List<UserDTO> getAllUsers() {
    try {
      return beanMapping.mapTo(userService.findAll(), UserDTO.class);
    } catch (FacadeLayerException | NoSuchElementException ex) {
      logger.warn("getAllUsers method invokes exception: " + ex);
      return Collections.emptyList();
    }
  }

  /*
   * @see com.cz.better.lawyer.facade.iface.UserFacade#getAllUsersWithParams()
   */
  @Override
  public List<UserDTO> getAllUsersWithParams(Predicate predicate, Pageable pageRequest) {
    try {
      return beanMapping.mapTo(userService.findAll(predicate, pageRequest), UserDTO.class);
    } catch (FacadeLayerException | NoSuchElementException ex) {
      logger.warn("getAllUsers method invokes exception: " + ex);
      return Collections.emptyList();
    }
  }

  /*
   * @see com.cz.better.lawyer.facade.iface.UserFacade#findByEmail(java.lang. String)
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<UserDTO> findByEmail(String email) {
    if (email == null)
      throw new NullPointerException("Email cannot be null to search by findByEmail method");
    if (email.isEmpty())
      throw new IllegalArgumentException("Email cannot be empty to search by findByEmail method");
    try {
      Optional<UserEntity> userEntity = userService.findByEmail(email);
      return userEntity.isPresent()
          ? Optional.ofNullable(beanMapping.mapTo(userEntity.get(), UserDTO.class))
          : Optional.empty();
    } catch (FacadeLayerException | NoSuchElementException ex) {
      logger.warn("findByEmail method invokes exception: " + ex);
      throw new FacadeLayerException("findByEmail method did not find any result: " + ex);
    }
  }

  /*
   * @see com.cz.better.lawyer.facade.iface.UserFacade#update(com.cz.better.lawyer.
   * dto.user.UserDTO)
   */
  @Override
  public Optional<UserDTO> update(UserDTO userDTO) {
    if (userDTO == null)
      throw new NullPointerException("User cannot be null");
    try {
      Optional<UserEntity> userEntity =
          userService.update(beanMapping.mapTo(userDTO, UserEntity.class));
      return userEntity.isPresent()
          ? Optional.ofNullable(beanMapping.mapTo(userEntity.get(), UserDTO.class))
          : Optional.empty();
    } catch (FacadeLayerException | NoSuchElementException ex) {
      logger.warn("update method invokes exception: " + ex);
      return Optional.empty();
    }
  }

  /*
   * @see com.cz.better.lawyer.facade.iface.UserFacade#remove(com.cz.better.lawyer.
   * dto.user.UserDTO)
   */
  @Override
  public void remove(UserDTO userDTO) {
    if (userDTO == null)
      throw new NullPointerException("User cannot be null");
    try {
      // userService.remove(userMapper.userDTOToUserEntity(userDTO));
      userService.remove(beanMapping.mapTo(userDTO, UserEntity.class));
    } catch (FacadeLayerException | NoSuchElementException ex) {
      logger.warn("remove method invokes exception: " + ex);
    }
  }

  /*
   * @see com.cz.better.lawyer.facade.iface.UserFacade#registerUser(com.cz.better.
   * lawyer.dto.user.UserDTO, java.lang.String)
   */
  @Override
  public Optional<UserDTO> registerUser(UserRegistrationDTO userDTO, String unencryptedPassword) {
    if (userDTO == null || unencryptedPassword == null || unencryptedPassword.isEmpty())
      throw new IllegalArgumentException(
          "userDTO parameter is null or unencryptedPassword is null or unencryptedPassword is empty in registerUser method");
    try {
      Optional<UserEntity> userEntity = userService
          .registerUser(beanMapping.mapTo(userDTO, UserEntity.class), unencryptedPassword);
      // podivat se na tuhle cast kodu jeste a vyoptimalizovat to...
      userDTO.setId(userEntity.get().getId());
      return userEntity.isPresent()
          ? Optional.ofNullable(beanMapping.mapTo(userEntity.get(), UserDTO.class))
          : Optional.empty();
    } catch (FacadeLayerException | NoSuchElementException ex) {
      logger.warn("registerUser method invokes exception: " + ex);
      return Optional.empty();
    }

  }

  /*
   * @see com.cz.better.lawyer.facade.iface.UserFacade#authenticate(com.cz.better.
   * lawyer.dto.user.UserDTO, java.lang.String)
   */
  @Override
  public boolean authenticate(UserAuthenticateDTO userDTO, String password) {
    if (userDTO == null)
      throw new IllegalArgumentException(
          "UserAuthenticateDTO userDTO parametr is null in authenticate method");
    if (userDTO.getEmail() == null)
      throw new IllegalArgumentException(
          "UserAuthenticateDTO userDTO should have email parameter filled.");
    try {
      UserEntity userEntity = beanMapping.mapTo(userDTO, UserEntity.class);
      return userService.authenticate(userEntity, password);
    } catch (FacadeLayerException | NoSuchElementException ex) {
      logger.warn("authenticate method invokes exception: " + ex);
      return false;
    }
  }

  @Override
  public boolean isAdmin(UserDTO userDTO) {
    // TODO Auto-generated method stub
    return false;
  }

}
