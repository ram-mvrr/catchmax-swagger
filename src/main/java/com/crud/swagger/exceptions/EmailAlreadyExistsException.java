package com.crud.swagger.exceptions;

public class EmailAlreadyExistsException extends Throwable {
    public EmailAlreadyExistsException(String emailAlreadyInUse) {
        super(emailAlreadyInUse);
    }
}
