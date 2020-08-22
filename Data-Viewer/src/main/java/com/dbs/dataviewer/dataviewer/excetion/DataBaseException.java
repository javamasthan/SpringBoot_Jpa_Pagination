package com.dbs.dataviewer.dataviewer.excetion;

public class DataBaseException extends RuntimeException{

private static final long serialVersionUID=1l;
	
	private CustomErrorMessage errorMsg;
	
	public DataBaseException(CustomErrorMessage errorMsg) {
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
