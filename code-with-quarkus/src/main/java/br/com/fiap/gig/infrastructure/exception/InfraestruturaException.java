package br.com.fiap.gig.infrastructure.exception;

public class InfraestruturaException extends  RuntimeException {

    public InfraestruturaException(String message) {
        super(message);
    }

    public InfraestruturaException(String message, Throwable cause) {
        super(message, cause);
    }
}
