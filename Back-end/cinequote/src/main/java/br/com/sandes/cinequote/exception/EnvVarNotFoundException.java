package br.com.sandes.cinequote.exception;

public class EnvVarNotFoundException extends RuntimeException {
    public EnvVarNotFoundException(String message) {
        super(message);
    }
}
