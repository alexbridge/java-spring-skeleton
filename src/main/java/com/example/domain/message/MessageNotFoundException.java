package com.example.domain.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends RuntimeException {

	public MessageNotFoundException(String id) {
		super("Message not found: " + id);
	}
}