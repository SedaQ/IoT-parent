package cz.vutbr.feec.iot.dto.user;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Pavel Šeda
 *
 */
public class UserAuthenticateDTO {

	@NotEmpty(message = "Please enter your email addresss.")
	private String email;

	@NotEmpty(message = "Please enter your password addresss.")
	@Size(min = 6, message = "Your password must have more than 6 characters")
	private String passwordHash;

	public UserAuthenticateDTO() {
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
		if (!(obj instanceof UserAuthenticateDTO))
			return false;
		UserAuthenticateDTO other = (UserAuthenticateDTO) obj;
		if (email == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!email.equals(other.getEmail()))
			return false;
		return true;
	}
}