package cz.vutbr.feec.iot.security;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cz.vutbr.feec.iot.config.WebConfig;
import cz.vutbr.feec.iot.facade.iface.UserFacade;
import cz.vutbr.feec.iot.rest.ApiEndpoints;
import cz.vutbr.feec.iot.rest.controller.UsersController;

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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;


@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class SecurityTest {

//  @Autowired
//  private WebApplicationContext wac;
//
//  private MockMvc mockMvc;
//
  // @BeforeClass
  // public void beforeClass() {
  // MockitoAnnotations.initMocks(this);
  // this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  // }
  //
  // @Test
  // public void testAccessBasicContext() throws Exception {
  // this.mockMvc.perform(get("/"))
  // .andExpect(status().isOk())
  // .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
  // }

  // @Test
  // public void testAccessDenied() throws Exception {
//    // @formatter:off
//    this.mockMvc.perform(get(ApiEndpoints.ROOT_URI_USERS).with(httpBasic("user","password")))
//        .andExpect(status().isOk())
//        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
//    // @formatter:on
  // }



}
