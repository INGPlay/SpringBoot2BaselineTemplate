package baseline.version2.springboot.exceptionHandler.exception;

import com.mall.spring_shopping.exceptionHandler.exception.abs.AbstractCustomException;
import org.springframework.http.HttpStatus;

public class KakaoPayFailureException extends AbstractCustomException {

    private HttpStatus statusCode;

    public KakaoPayFailureException(String message) {
        super(message);
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public KakaoPayFailureException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    @Override
    public HttpStatus getStatusCode() {
        return this.statusCode;
    }
}
