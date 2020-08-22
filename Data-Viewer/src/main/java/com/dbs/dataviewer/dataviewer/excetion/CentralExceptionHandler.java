package com.dbs.dataviewer.dataviewer.excetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralExceptionHandler {

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<CustomErrorMessage> exception(HttpMessageNotReadableException exception) {
		CustomErrorMessage cem = new CustomErrorMessage(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE,
				"Please Provide Fields in proper format");
		return new ResponseEntity<CustomErrorMessage>(cem, HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE);
	}

	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<CustomErrorMessage> exception(HttpRequestMethodNotSupportedException exception) {
		CustomErrorMessage cem = new CustomErrorMessage(HttpStatus.METHOD_NOT_ALLOWED,
				"Please select Proper Request Method ");
		return new ResponseEntity<CustomErrorMessage>(cem, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(value = FieldValidationException.class)
	public ResponseEntity<CustomErrorMessage> exception(FieldValidationException exception) {
		return new ResponseEntity<CustomErrorMessage>(exception.getErrorMsg(), exception.getErrorMsg().getStatus());
	}

	@ExceptionHandler(value = DataBaseException.class)
	public ResponseEntity<CustomErrorMessage> exception(DataBaseException exception) {
		return new ResponseEntity<CustomErrorMessage>(exception.getErrorMsg(), exception.getErrorMsg().getStatus());
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<CustomErrorMessage> exception(Exception exception) {
		
		CustomErrorMessage cem = new CustomErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
				"Request Failed Please try Again!!! ");
		return new ResponseEntity<CustomErrorMessage>(cem, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
