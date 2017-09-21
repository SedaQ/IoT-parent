package cz.vutbr.feec.iot.dto;

import cz.vutbr.feec.iot.validation.ValidPassword;

/**
 * @author Pavel Šeda
 *
 */
public class PasswordDTO {

	private String oldPassword;

	@ValidPassword
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
