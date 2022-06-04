package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "Favorite Already Exists.")
public class FavoriteAlreadyExistsException extends RuntimeException {

}
