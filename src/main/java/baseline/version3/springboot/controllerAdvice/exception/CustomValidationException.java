package baseline.version3.springboot.controllerAdvice.exception;

import baseline.version3.springboot.controllerAdvice.exception.abs.AbstractCustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@Getter
public class CustomValidationException extends AbstractCustomException {

    private final BindingResult bindingResult;

    public CustomValidationException(BindingResult bindingResult) {
        super("Validation Error");
        this.bindingResult = bindingResult;
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
