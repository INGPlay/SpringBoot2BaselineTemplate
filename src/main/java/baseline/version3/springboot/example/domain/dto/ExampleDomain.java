package baseline.version3.springboot.example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ExampleDomain {

    @Getter @Setter
    public static class EditDTO {
        private Long id;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class ResponseVO{
        private Long id;
        private String content;
    }
}
