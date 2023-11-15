package baseline.version2.springboot.exceptionHandler.exception.abs;

import org.springframework.http.HttpStatus;

abstract public class AbstractCustomException extends RuntimeException {

    public AbstractCustomException(String message) {
        super(message);
    }

    abstract public HttpStatus getStatusCode();
}
