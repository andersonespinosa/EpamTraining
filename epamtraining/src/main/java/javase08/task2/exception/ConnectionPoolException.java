package javase08.task2.exception;

public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
