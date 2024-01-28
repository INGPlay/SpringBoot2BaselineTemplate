package baseline.version3.springboot.pageAdmin.domain.concatPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ConcatPageRequest {
    @Getter
    @Setter
    public static class RequestDynamicQueryOne {
        private String concatPagePath;
    }
}
