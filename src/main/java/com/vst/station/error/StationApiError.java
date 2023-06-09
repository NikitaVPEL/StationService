package com.vst.station.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vst.station.exception.ChargerNotFoundException;
import com.vst.station.exception.InValidDataException;
import com.vst.station.exception.InValidIdExcepetion;
import com.vst.station.exception.StationException;
import com.vst.station.exception.StationIdNotAcceptableException;
import com.vst.station.exception.StationNotFoundException;

@RestControllerAdvice
public class StationApiError {

	String message = "error";

	@ExceptionHandler(StationNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> userNotFound(StationNotFoundException ex) {
		Map<String, Object> errorMap = new HashMap<>();
		StationErrorResponse response = new StationErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setStatusCode("404");
		response.setTimeStamp(LocalDateTime.now());
		errorMap.put(message, response);
		return errorMap;
	}

	@ExceptionHandler(StationIdNotAcceptableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public Map<String, Object> idNotFound(StationIdNotAcceptableException ex) {
		Map<String, Object> errorMap = new HashMap<>();
		StationErrorResponse response = new StationErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		response.setStatusCode("406");
		response.setTimeStamp(LocalDateTime.now());
		errorMap.put(message, response);
		return errorMap;
	}

	@ExceptionHandler(InValidIdExcepetion.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public Map<String, Object> idNotValied(InValidIdExcepetion ex) {
		Map<String, Object> errorMap = new HashMap<>();
		StationErrorResponse response = new StationErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		response.setStatusCode("406");
		response.setTimeStamp(LocalDateTime.now());
		errorMap.put(message, response);
		return errorMap;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ExceptionHandler(InValidDataException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> inValidData(InValidDataException ex) {
		Map<String, Object> errorMap = new HashMap<>();
		StationErrorResponse response = new StationErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setStatusCode("404");
		response.setTimeStamp(LocalDateTime.now());
		errorMap.put(message, response);
		return errorMap;
	}
	
	
	@ExceptionHandler(ChargerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> chargerNotFound(ChargerNotFoundException ex) {
		Map<String, Object> errorMap = new HashMap<>();
		StationErrorResponse response = new StationErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setStatusCode("404");
		response.setTimeStamp(LocalDateTime.now());
		errorMap.put(message, response);
		return errorMap;
	}
	
	@ExceptionHandler(StationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> stationException(StationException ex) {
		Map<String, Object> errorMap = new HashMap<>();
		StationErrorResponse response = new StationErrorResponse();
		
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setStatusCode("502");
		response.setServiceName(ex.getServiceName());
		response.setFunctionality(ex.getFunctionality());
		response.setLineNumber(ex.getLineNumber());
		response.setMessage(ex.getMessage());
		response.setMethodName(ex.getMethodName());
		response.setServiceCode(ex.getServiceCode());
		response.setClassName(ex.getClassName());
		response.setTimeStamp(LocalDateTime.now());

		errorMap.put(message, response);
		return errorMap;
	}
	
	
	
	

}
