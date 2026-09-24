package br.com.ctw.gestaoentrega.exceptions;

/**
 * Exceção lançada quando um recurso não for encontrado
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
