package cz.vutbr.feec.iot.dto.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cz.vutbr.feec.iot.dto.role.RoleDTO;
import cz.vutbr.feec.iot.validation.PasswordMatches;
import cz.vutbr.feec.iot.validation.ValidEmail;
import cz.vutbr.feec.iot.validation.ValidPassword;

/**
 * @author Pavel Šeda
 *
 */
@PasswordMatches
public class UserRegistrationDTO {

	private Long id;
	@NotNull
	@NotEmpty(message = "Please enter your email addresss.")
	@ValidEmail
	private String email;
	@NotNull
	@NotEmpty(message = "Please enter your password addresss.")
	@ValidPassword
	private String passwordHash;
	@NotNull
	@Size(min = 1)
	private String matchingPassword;
	@NotNull
	private List<RoleDTO> roles = new ArrayList<>(0);

	public UserRegistrationDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

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
		if (!(obj instanceof UserRegistrationDTO))
			return false;
		UserRegistrationDTO other = (UserRegistrationDTO) obj;
		if (email == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!email.equals(other.getEmail()))
			return false;
		return true;
	}
}