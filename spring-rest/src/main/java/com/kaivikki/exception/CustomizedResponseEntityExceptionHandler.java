package com.kaivikki.exception;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;



@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		HttpStatus httpStatus=null;
		if(ex instanceof UserNotFoundException) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false),
				ExceptionUtils.getStackTrace(ex));
		return new ResponseEntity<>(errorDetails,null!=httpStatus?httpStatus:HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<FieldError> fieldErrors = ex.getFieldErrors();
		JsonObject jsonObject = new JsonObject();
		fieldErrors.forEach(fe->jsonObject.addProperty(fe.getField(), fe.getDefaultMessage()));
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				jsonObject.toString(),
				request.getDescription(false),
				ExceptionUtils.getStackTrace(ex));
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}

@AllArgsConstructor
@Getter
class ErrorDetails {
	private LocalDateTime timestamp;
	private String message;
	private String requestDetails;
	private String stackTrace;
}