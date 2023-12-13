package com.example.demo.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.ProductNotFoundException;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class ProductExceptionController { 

//	@ExceptionHandler(ProductNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public String exception(ProductNotFoundException ex){
//		return ex.getMessage();
//		
//	}
//	
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,WebRequest webRequest){
//		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
//		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//		// TODO Auto-generated method stub
//		Map<String, Object> responseBody=new LinkedHashMap<>();
//		responseBody.put("timestamp", new Date());
//		responseBody.put("status", status.value());
//		
//		List<FieldError> fieldErrors =ex.getBindingResult().getFieldErrors();
//		
//		List<String> listErrors=new ArrayList<>();
//		
//		for(FieldError fieldError:fieldErrors) {
//			String  errorMessage=fieldError.getDefaultMessage();
//			listErrors.add(errorMessage);
//			
//		}
//		responseBody.put("errors", listErrors);
//		return new ResponseEntity<>(responseBody, headers,status);
//		
//		
//	}
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex){
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ProductNotFoundException.class)
	public Map<String, String> productServiceException(ProductNotFoundException ex){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
}

	