package cz.vutbr.feec.iot.dto.role;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.view.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ApiModel and @ApiModelProperty annotation are annotations used for REST API
 *           documentation of these entities. In order to do not mix these
 *           annotations with others they are places near getters.
 * @author Pavel Šeda
 *
 */
@ApiModel(value = "Role DTO", description = "Role DTO description")
public class RoleDTO {

	private Long id;
	private List<UserDTO> users = new ArrayList<>(0);
	@NotNull
	@JsonView(View.Summary.class)
	private String role;

	public RoleDTO() {
	}

	public RoleDTO(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UserDTO> getUsers() {
		// return Collections.unmodifiableList(user);
		return users;
	}

	public void setUsers(List<UserDTO> user) {
		this.users = user;
	}

	public void addUser(UserDTO user) {
		this.users.add(user);
	}

	@ApiModelProperty(value = "role", dataType = "java.lang.String", example = "ADMIN, USER", required = true)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return 31 * 1 + ((role == null) ? 0 : role.hashCode());
	}

}
