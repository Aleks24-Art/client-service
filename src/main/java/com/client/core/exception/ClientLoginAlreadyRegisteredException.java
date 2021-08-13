package com.client.core.exception;

public class ClientLoginAlreadyRegisteredException extends RuntimeException {

    public ClientLoginAlreadyRegisteredException(String login) {
        super("Client login already registered = " + login);
    }
}
