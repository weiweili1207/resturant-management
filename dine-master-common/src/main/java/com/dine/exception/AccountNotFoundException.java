package com.dine.exception;

/**
 * account not found exception
 */
public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }

}
