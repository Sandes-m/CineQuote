package br.com.sandes.cinequote.exception;

public class AiRequestFailedException extends RuntimeException {

    public AiRequestFailedException(String message) {
        super(message);
    }
}
