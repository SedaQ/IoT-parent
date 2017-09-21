package cz.vutbr.feec.iot.rest.controller;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import cz.vutbr.feec.iot.config.WebConfig;
import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.facade.iface.UserFacade;
import cz.vutbr.feec.iot.rest.ApiEndpoints;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class UsersControllerTest extends AbstractTestNGSpringContextTests {

  @Mock
  private UserFacade userFacade;

  @Autowired
  @InjectMocks
  private UsersController usersController;

  private MockMvc mockMvc;

  @BeforeClass
  public void beforeClass() {
    MockitoAnnotations.initMocks(this);
    mockMvc = standaloneSetup(usersController)
        .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
  }

//  @Test
//  public void getAllUsers() {
//    // @formatter:off
//    doReturn(Collections.unmodifiableCollection(this.createUsers()))
//        .when(userFacade).getAllUsers();
//
//    try {
//      mockMvc.perform(get(ApiEndpoints.ROOT_URI_USERS))
//          .andExpect(status().isOk())
//          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    // @formatter:on
//  }

  private List<UserDTO> createUsers() {
    UserDTO user1 = new UserDTO();
    user1.setEmail("test123132@asdasd.cz");
    user1.setEnabled(true);
    user1.setId(12345L);
    user1.setPasswordHash("pasdoasdoasod!AASS123");

    UserDTO user2 = new UserDTO();
    user2.setEmail("test12313123123asdasd2@asdasd.cz");
    user2.setEnabled(true);
    user2.setId(1234L);
    user2.setPasswordHash("pasdoasdoaasdadasdasdsod!AASS123");

    return Arrays.asList(user1, user2);
  }

}
