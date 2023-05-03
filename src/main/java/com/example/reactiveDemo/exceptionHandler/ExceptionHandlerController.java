package com.example.reactiveDemo.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import com.example.reactiveDemo.errorResponse.pojo.ErrorResponse;
import com.example.reactiveDemo.exception.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.status(404)
				.body(new ErrorResponse(ex.getMessage()));
	}

}
