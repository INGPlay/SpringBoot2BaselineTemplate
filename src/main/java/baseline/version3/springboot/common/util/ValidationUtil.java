package baseline.version3.springboot.common.util;

import baseline.version3.springboot.exceptionHandler.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

@Slf4j
public final class ValidationUtil {
    public static void checkErrors(BindingResult bindingResult) {
        log.info("BindingResult : {}", bindingResult);
        if (bindingResult.hasErrors()){
            throw new CustomValidationException(bindingResult);
        }
    }
}
