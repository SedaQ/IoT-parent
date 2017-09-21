package cz.vutbr.feec.iot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cz.vutbr.feec.iot.dto.user.UserRegistrationDTO;

/**
 * @author Pavel Šeda
 *
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		UserRegistrationDTO user = (UserRegistrationDTO) obj;
		return user.getPasswordHash().equals(user.getMatchingPassword());
	}
}