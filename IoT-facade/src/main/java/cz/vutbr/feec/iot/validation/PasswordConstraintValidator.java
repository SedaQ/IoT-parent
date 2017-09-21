package cz.vutbr.feec.iot.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

import com.google.common.base.Joiner;

/**
 * @author Pavel Šeda
 *
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public void initialize(final ValidPassword arg0) {

	}

	/**
	 * Password must be between 8 and 30 characters must contain at least 1
	 * UpperCase character and must contain at least 1 digital character and at
	 * least 1 special character
	 */
	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext context) {
		final PasswordValidator validator = new PasswordValidator(
				Arrays.asList(new LengthRule(8, 30), new UppercaseCharacterRule(1), new DigitCharacterRule(1),
						new SpecialCharacterRule(1), new WhitespaceRule()));
		final RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(Joiner.on("\n").join(validator.getMessages(result)))
				.addConstraintViolation();
		return false;
	}

}
