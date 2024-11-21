package com.crud.swagger.exceptions;

public class UsernameAlreadyExistsException extends Throwable {
    public UsernameAlreadyExistsException(String usernameAlreadyInUse) {
        super(usernameAlreadyInUse);

    }
}
