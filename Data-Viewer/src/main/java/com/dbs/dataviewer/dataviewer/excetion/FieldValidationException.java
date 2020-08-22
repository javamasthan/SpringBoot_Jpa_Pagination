package com.dbs.dataviewer.dataviewer.excetion;

public class FieldValidationException extends RuntimeException {
	private static final long serialVersionUID=1l;
	
	private CustomErrorMessage errorMsg;
	
	public FieldValidationException(CustomErrorMessage errorMsg) {
		super();
		this.errorMsg=errorMsg;
		
	}

	public CustomErrorMessage getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(CustomErrorMessage errorMsg) {
		this.errorMsg = errorMsg;
	}
	 

}
