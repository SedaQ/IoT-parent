package cz.vutbr.feec.iot.dto.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import cz.vutbr.feec.iot.dto.role.RoleDTO;
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
@ApiModel(value = "User DTO", description = "User DTO description")
public class UserDTO {

	private Long id;
	@JsonView(View.Summary.class)
	@NotEmpty(message = "Please enter your email addresss.")
	private String email;
	@NotEmpty(message = "Please enter your password addresss.")
	@Size(min = 8, message = "Your password must have more than 6 characters")
	private String passwordHash;
	@JsonView(View.Summary.class)
	private boolean enabled;
	@NotNull
	@JsonView(View.Summary.class)
	private List<RoleDTO> roles = new ArrayList<>(0);

	public UserDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "email", dataType = "java.lang.String", required = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ApiModelProperty(value = "passwordHash", dataType = "boolean", required = true)
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@ApiModelProperty(value = "roles", dataType = "java.util.List", example = "ADMIN, USER", required = true)
	public List<RoleDTO> getRoles() {
		// return Collections.unmodifiableList(roles);
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public void addRole(RoleDTO role) {
		this.roles.add(role);
	}

	@ApiModelProperty(value = "enabled", dataType = "boolean", required = true)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		return 31 * 1 + ((email == null) ? 0 : email.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserDTO))
			return false;
		UserDTO other = (UserDTO) obj;
		if (email == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!email.equals(other.getEmail()))
			return false;
		return true;
	}
}
