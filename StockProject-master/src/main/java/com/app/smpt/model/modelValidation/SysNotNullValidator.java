package com.app.smpt.model.modelValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.app.smpt.utils.SysUtilities;


public class SysNotNullValidator implements ConstraintValidator<SysNotNull, Object>{
	@SuppressWarnings("unused")
	private SysNotNull malNotNull;	
	
	@Override
	public void initialize(SysNotNull malNotNull) {		
		this.malNotNull = malNotNull;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		
		boolean isValid = ! SysUtilities.isEmpty(value);
		
		return isValid;
	}

}
