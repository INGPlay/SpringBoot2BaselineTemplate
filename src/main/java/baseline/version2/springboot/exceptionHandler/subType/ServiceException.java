package baseline.version2.springboot.exceptionHandler.subType;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum ServiceException {
    NOT_FOUND_IN_REPOSITORY("찾을 수 없는 데이터입니다.", HttpStatus.NOT_FOUND)
    ;

    private String errorMessage;
    private HttpStatus StatusCode;
}
