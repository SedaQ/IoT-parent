package cz.vutbr.feec.iot.config;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PropertiesConfig.class)
public class PropertiesConfigTest {

  @Value("${inmemoryauth.username}")
  private String username;

  @Test
  public final void givenContextIsInitialized() {
    System.out.println("inTest: " + username);
    //Assert.assertEquals("vutbrfekt", username);
  }
}
