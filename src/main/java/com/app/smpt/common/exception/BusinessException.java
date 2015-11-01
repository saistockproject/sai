package com.app.smpt.common.exception;

import com.app.smpt.common.LocaleMessage;
import com.app.smpt.common.SysLogger;
import com.app.smpt.common.SysLoggerFactory;
import com.app.smpt.utils.SpringAppContext;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	protected LocaleMessage localeMessage = (LocaleMessage) SpringAppContext.getBean(LocaleMessage.class);
	public SysLogger logger = SysLoggerFactory.getLogger(this.getClass());

	public BusinessException(String errorMessageKey, String errorMessageParam[], Exception e) {
		super(e);
		resolveMessage(errorMessageKey, errorMessageParam);
		// logger.error(e, message);

	}

	public BusinessException(String errorMessageKey, String[] errorMessageParam) {
		super();
		resolveMessage(errorMessageKey, errorMessageParam);
	}

	public BusinessException(String errorMessageKey) {
		super();
		resolveMessage(errorMessageKey, null);
	}

	public BusinessException(String errorMessageKey, Exception e) {
		super(e);
		resolveMessage(errorMessageKey, null);
		// logger.error(e, message);

	}

	private void resolveMessage(String errorMessageKey, String[] errorMessageParam) {
		try {
			message = localeMessage.getMessage(errorMessageKey, errorMessageParam);
		} catch (Exception ex) {
			message = errorMessageKey;
		}
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
