package br.com.ctw.gestaoentrega.exceptions;

/**
 * Exceção lançada quando já existe um Motorista com um CNH igual ao recebido
 */
public class AlreadyExistsMotoristaException extends RuntimeException {
    public AlreadyExistsMotoristaException(String message) {
        super(message);
    }
}
