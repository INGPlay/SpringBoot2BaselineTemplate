package baseline.version3.springboot.common.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ExceptionForm {
    private Boolean result;

    private Map<String, Object> data;

    public ExceptionForm(boolean result) {
        this.result = result;
    }
}
