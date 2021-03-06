package com.app.smpt.common.validation;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.app.smpt.utils.SysUtilities;


public class SysDateValidator implements ConstraintValidator<SysDate, Object>{
	
	@SuppressWarnings("unused")
	private SysDate maDate;	
	
	public void initialize(SysDate maDate) {		
		this.maDate = maDate;
	}

	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		Date date = (Date)value;
		
		if (SysUtilities.isEmpty(value)) return true;
		
		return SysUtilities.validateDateRange(date);
	}

}
