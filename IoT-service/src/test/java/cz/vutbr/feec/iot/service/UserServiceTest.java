package cz.vutbr.feec.iot.service;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.vutbr.feec.iot.dao.UserRepository;
import cz.vutbr.feec.iot.entity.RoleEntity;
import cz.vutbr.feec.iot.entity.UserEntity;
import cz.vutbr.feec.iot.security.UserPasswordEncryption;
import cz.vutbr.feec.iot.service.iface.UserService;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Pavel Šeda
 *
 */
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserPasswordEncryption userPasswordEncryption;

  private UserService userService;

  private UserEntity userEntity;

  @BeforeClass
  public void beforeClass() {
    MockitoAnnotations.initMocks(this);
    userService = new UserServiceImpl(userRepository, userPasswordEncryption);
  }

  @BeforeMethod
  public void beforeMethod() {
    userEntity = new UserEntity();
    userEntity.setEmail("psop@email.cz");
    userEntity.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
  }
  
  @AfterMethod
  public void afterMethod() {
    reset(userRepository);
  }

  @Test
  public void testRegisterUser() {
    when(userPasswordEncryption.createHash("1234123as"))
        .thenReturn("NEWtestHashtestHashtestHashtestHash7841267871s!@$%");
    userService.registerUser(userEntity, "1234123as");
    verify(userRepository, times(1)).save(userEntity);
    assertEquals(userEntity.getPasswordHash(),
        "NEWtestHashtestHashtestHashtestHash7841267871s!@$%");
  }

  @Test
  public void testAuthenticate() {
    when(userPasswordEncryption.validatePassword(any(String.class), any(String.class)))
        .thenReturn(true);
    userService.authenticate(userEntity, "1234123as");
    verify(userPasswordEncryption, times(1)).validatePassword("1234123as",
        userEntity.getPasswordHash());
  }

  @Test
  public void testFindById() {
    userService.findById(Long.MAX_VALUE);
    verify(userRepository, times(1)).findOne(Long.MAX_VALUE);
  }

  @Test
  public void testUpdate() {
    userEntity.setEmail("bad.boy@thuglife.org");
    userService.update(userEntity);
    verify(userRepository, times(1)).save(userEntity);
  }

  @Test
  public void testRemove() {
    userService.remove(userEntity);
    verify(userRepository, times(1)).delete(userEntity);
  }

  @Test
  public void testFindAll() {
    userService.findAll();
    verify(userRepository, times(1)).findAll();
  }

  @Test
  public void testFindByEmail() {
    userService.findByEmail("franta@bfu.cz");
    verify(userRepository, times(1)).findByEmail(any(String.class));
  }

  @Test(expectedExceptions = {NullPointerException.class})
  public void testFindByEmailNull() {
    userService.findByEmail(null);
    fail("Expected NullPointerException");
  }

  // try to test this method more properly
  @Test
  public void testIsAdmin() {
    userService.isAdmin(userEntity);
    verify(userRepository, times(1)).findOne(any(Long.class));
  }

}
