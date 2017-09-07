package com.libertymutual.goforcode.penneForYourThoughts.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such recipe has been found")  // 404
public class RecipeNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
}
