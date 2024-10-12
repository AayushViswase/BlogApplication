package com.Blog.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	Long fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("%s not found %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
public class RecordsNotFoundException extends Exception {

	public RecordsNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public RecordsNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
