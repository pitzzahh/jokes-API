package io.github.pitzzahh.jokes.exception;

/**
 * The category not found exception
 */
public class CategoryNotFoundException extends RuntimeException {

    /**
     * The category not found exception
     * @param message the message
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
