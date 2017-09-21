package cz.vutbr.feec.iot.rest.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.vutbr.feec.iot.rest.ApiEndpoints;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author Pavel Šeda
 */
@RestController
@RequestMapping(value = ApiEndpoints.BASE_URL)
@Api(value = "Main REST API Entry Point",
    authorizations = {@Authorization(value = "httpbasic", scopes = {})})
public class MainController {

  final static Logger logger = LogManager.getLogger(MainController.class);

  /**
   * The main entry point of the REST API Provides access to all the resources with links to
   * resource URIs Can be even extended further so that all the actions on all the resources are
   * available and can be reused in all the controllers (possibly in full HATEOAS style)
   * 
   * curl -i -X GET ~/api/rest/
   * 
   * @return resources uris
   */
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Get all other resources necessary and link them with HATEAOS")
  public final HttpEntity<Resource<String>> getResources() {
    logger.debug("rest getResources main");

    Resource<String> resource = new Resource<>("REST Resources Home");
    resource.add(linkTo(MainController.class).withSelfRel());
    resource.add(linkTo(UsersController.class).withRel("users"));

    return ResponseEntity.ok().body(resource);
  }
}
