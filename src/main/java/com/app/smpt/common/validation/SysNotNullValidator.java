package com.app.smpt.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.app.smpt.utils.SysUtilities;


public class SysNotNullValidator implements ConstraintValidator<SysNotNull, Object>{
	@SuppressWarnings("unused")
	private SysNotNull malNotNull;	
	
	public void initialize(SysNotNull malNotNull) {		
		this.malNotNull = malNotNull;
	}

	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		
		boolean isValid = ! SysUtilities.isEmpty(value);
		
		return isValid;
	}

}
