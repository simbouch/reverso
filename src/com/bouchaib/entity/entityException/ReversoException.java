package com.bouchaib.entity.entityException;

/**
 * The ReversoException class represents
 * an exception that is thrown when a
 * reversal operation cannot be
 * performed.
 */
public class ReversoException
        extends Exception {

    /**
     * Constructs a new ReversoException
     * object.
     */
    public ReversoException() {}

    /**
     * Constructs a new ReversoException
     * object with the specified detail
     * message.
     * @param message the detail message
     */
    public ReversoException(
            String message) {
        super(message);
    }
}
