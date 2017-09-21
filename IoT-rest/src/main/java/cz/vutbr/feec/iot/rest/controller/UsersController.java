package cz.vutbr.feec.iot.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mysema.query.types.Predicate;

import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.dto.user.UserRegistrationDTO;
import cz.vutbr.feec.iot.entity.UserEntity;
import cz.vutbr.feec.iot.facade.iface.UserFacade;
import cz.vutbr.feec.iot.rest.ApiEndpoints;
import cz.vutbr.feec.iot.view.View;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;

/**
 * @author Pavel Šeda
 */
@RestController
@RequestMapping(value = ApiEndpoints.ROOT_URI_USERS)
@Api(value = "Users", authorizations = {@Authorization(value = "httpbasic", scopes = {})})
public class UsersController {

  private UserFacade userFacade;

  @Inject
  public UsersController(UserFacade userFacade) {
    this.userFacade = userFacade;
  }

  @JsonView(View.Summary.class)
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get all users with parameters.",
      response = UserDTO.class, responseContainer = "collection", produces = "application/json")
  public ResponseEntity<List<UserDTO>> getAllUsersWithParams(
      @QuerydslPredicate(root = UserEntity.class) Predicate predicate, Pageable pageRequest,
      @RequestParam MultiValueMap<String, String> parameters) {
    return new ResponseEntity<>(userFacade.getAllUsersWithParams(predicate, pageRequest),
        HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "POST", value = "Creates User", response = UserDTO.class,
      produces = "application/json", consumes = "application/json")
  public ResponseEntity<UserDTO> createUser(
      @ApiParam(value = "Create given User. User values must be valid.",
          required = true) @Valid @RequestBody UserRegistrationDTO userDTO) {
    Optional<UserDTO> registeredUserDTO =
        userFacade.registerUser(userDTO, userDTO.getPasswordHash());
    if (registeredUserDTO.isPresent())
      return new ResponseEntity<>(registeredUserDTO.get(), HttpStatus.CREATED);
    else
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  // @RequestMapping(value = "/{id}", method = RequestMethod.GET,
  // produces = MediaType.APPLICATION_JSON_VALUE)
  // public ResponseEntity<UserDTO> handleBadRequest() {
  // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  // }

  @JsonView(View.Summary.class)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get user by ID", response = UserDTO.class,
      produces = "application/json")
  public ResponseEntity<UserDTO> getUserById(@ApiParam(value = "User must be find by id.",
      required = true) @PathVariable("id") final Long id) {
    Optional<UserDTO> userDTO = userFacade.findById(id);
    if (userDTO.isPresent())
      return new ResponseEntity<>(userDTO.get(), HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "PUT", value = "Update User object.", response = UserDTO.class,
      produces = "application/json", consumes = "application/json")
  public ResponseEntity<UserDTO> updateUser(
      @ApiParam(value = "Given user id.", required = true) @PathVariable("id") Long id,
      @ApiParam(value = "Given user object. User object must be valid!",
          required = true) @Valid @RequestBody UserDTO userDTO) {
    Optional<UserDTO> updatedUserDTO = userFacade.update(userDTO);
    if (updatedUserDTO.isPresent())
      return new ResponseEntity<>(updatedUserDTO.get(), HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "DELETE", value = "Delete User object by id.",
      response = UserDTO.class, produces = "application/json", consumes = "application/json")
  public ResponseEntity<UserDTO> deleteUser(
      @ApiParam(value = "Given user id.", required = true) @PathVariable("id") Long id) {
    Optional<UserDTO> userDTO = userFacade.findById(id);
    if (!userDTO.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    userFacade.remove(userDTO.get());
    return new ResponseEntity<>(userDTO.get(), HttpStatus.OK);
  }

}
