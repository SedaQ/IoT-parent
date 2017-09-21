package cz.vutbr.feec.iot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cz.vutbr.feec.iot.config.ServiceConfigurationTest;

/**
 * @author Pavel Šeda
 *
 */
@ContextConfiguration(classes = ServiceConfigurationTest.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserPasswordEncryptionTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private UserPasswordEncryption userPwdEncryption;

  private String password;
  private String passwordHash;

  @BeforeClass()
  public void init() {
    password = "testPassword";
  }

  @Test
  public void testCreateHash() {
    passwordHash = userPwdEncryption.createHash(password);
    Assert.assertNotEquals(password, passwordHash);
  }

  @Test
  public void testValidatePassword() {
    Assert.assertTrue(userPwdEncryption.validatePassword(password, passwordHash));
  }

}
