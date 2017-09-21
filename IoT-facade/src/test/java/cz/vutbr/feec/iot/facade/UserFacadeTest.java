package cz.vutbr.feec.iot.facade;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.vutbr.feec.iot.config.FacadeConfiguration;
import cz.vutbr.feec.iot.dto.user.UserAuthenticateDTO;
import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.dto.user.UserRegistrationDTO;
import cz.vutbr.feec.iot.entity.UserEntity;
import cz.vutbr.feec.iot.facade.iface.UserFacade;
import cz.vutbr.feec.iot.mapping.BeanMapping;
import cz.vutbr.feec.iot.service.iface.UserService;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Optional;

/**
 * @author Pavel Šeda
 *
 */
@ContextConfiguration(classes = FacadeConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserFacadeTest {
  @Mock
  private UserService userService;
  @Mock
  private BeanMapping beanMapping;
  private UserFacade userFacade;
  private UserDTO user1;
  private UserRegistrationDTO user2;

  @BeforeClass
  public void init() {
    MockitoAnnotations.initMocks(this);
    doReturn(Optional.of(new User())).when(beanMapping).mapTo(any(UserDTO.class), eq(User.class));
    doReturn(Optional.of(new User())).when(beanMapping).mapTo(any(UserRegistrationDTO.class),
        eq(User.class));
    doReturn(Optional.of(new UserDTO())).when(beanMapping).mapTo(any(User.class),
        eq(UserDTO.class));

    userFacade = new UserFacadeImpl(userService, beanMapping);
  }

  @BeforeMethod
  public void beforeMethod() {
    user1 = new UserDTO();
    user2 = new UserRegistrationDTO();
    user2.setEmail("testEmail@seznam.cz");
    user2.setPasswordHash("testPassword!@!@D123123");
  }

  @AfterMethod
  public void afterMethod() {
    reset(userService);
  }

//  @Test
//  public void testGetUserById() {
//    userFacade.findById(5L);
//    verify(userService, times(1)).findById(any(Long.class));
//  }

  // @Test
  // public void testGetUserByEmail() {
  // userFacade.findByEmail("testEmail@seznam.cz");
  // verify(userService, times(1)).findByEmail(any(String.class));
  // }

  // @Test
  // public void testRegisterUser() {
  // userFacade.registerUser(user2, "testPassword!@!@D123123");
  // verify(userService, times(1)).registerUser(any(UserEntity.class), any(String.class));
  // }

  // @Test
  // public void testUpdate() {
  // userFacade.update(new UserDTO());
  // verify(userService, times(1)).update(any(UserEntity.class));
  // }
  //
  // @Test
  // public void testDeleteUser() {
  // userFacade.remove(new UserDTO());
  // verify(userService, times(1)).remove(any(UserEntity.class));
  // }
  //
  // @Test
  // public void testRegisterUser() {
  // userFacade.registerUser(user2, "testPassword!@!@D123123");
  // verify(userService, times(1)).registerUser(any(UserEntity.class), any(String.class));
  // }
  //
  // @Test
  // public void testAuthenticate() {
  // UserAuthenticateDTO userForAuth = new UserAuthenticateDTO();
  // userForAuth.setEmail("pavelseda@email.cz");
  // userForAuth.setPasswordHash("password");
  // userFacade.authenticate(userForAuth, "password");
  // verify(userService, times(1)).authenticate(any(UserEntity.class), any(String.class));
  // }

}
