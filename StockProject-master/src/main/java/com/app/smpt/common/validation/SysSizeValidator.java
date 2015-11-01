package com.app.smpt.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.app.smpt.utils.SysUtilities;


public class SysSizeValidator implements ConstraintValidator<SysSize, String> {
	private SysSize malSize;

	public void initialize(SysSize size) {
		this.malSize = size;
	}

	public boolean isValid(String value, ConstraintValidatorContext arg1) {

		boolean isValid = SysUtilities.validateFieldLength(
				Integer.valueOf(malSize.max()), Integer.valueOf(malSize.min()),
				value);
		
		return isValid;
	}

}
