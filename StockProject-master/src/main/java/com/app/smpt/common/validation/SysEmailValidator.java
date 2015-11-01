package com.app.smpt.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.app.smpt.utils.SysUtilities;


public class SysEmailValidator implements ConstraintValidator<SysEmail, Object>{
	@SuppressWarnings("unused")
	private SysEmail maEmail;	
	
	public void initialize(SysEmail maEmail) {		
		this.maEmail = maEmail;
	}

	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		
		boolean isValid = false;
		
		//Email validation should not validate email as a required value, so only
		// execute validation when email is not null.
		if(SysUtilities.isEmpty(value)){
			isValid = true;
		}
		else{
			isValid = SysUtilities.isValidEmailAddress(String.valueOf(value));
		}
		return isValid;
	}
}
