package com.bookstore.aiti.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("pls your request method is wrong");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("media type not supported");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Path variable not found");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("wrong parameter");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("type mismatch");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("messag not readable");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);
	}

	@ExceptionHandler(BookNotFoundException.class)
	protected ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex){
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Books not found");
		ApiError errors = new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	protected ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex){
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("id not found");
		ApiError errors = new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex){
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("exception occurred");
		ApiError errors = new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
}
