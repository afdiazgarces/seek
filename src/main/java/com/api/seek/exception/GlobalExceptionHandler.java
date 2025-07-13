package com.api.seek.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.seek.dto.ApiErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.joining(", "));

		return ApiErrorResponse.builder().status(400).error("Bad Request").message(errorMessage)
				.path(request.getRequestURI()).timestamp(LocalDateTime.now().toString()).build();
	}

	@ExceptionHandler(AuthenticationServiceException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ApiErrorResponse handleAuthException(AuthenticationServiceException ex, HttpServletRequest request) {

		return ApiErrorResponse.builder().status(401).error("UNAUTHORIZED").message(ex.getMessage())
				.path(request.getRequestURI()).timestamp(LocalDateTime.now().toString()).build();
	}
}
