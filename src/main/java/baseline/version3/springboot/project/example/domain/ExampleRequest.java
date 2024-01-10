package baseline.version3.springboot.project.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ExampleRequest {

    @Getter @Setter
    public static class Request {
        private Long id;
        private String content;
    }

    @Getter
    @Setter
    public static class Event {
        private Long id;
        private String content;
    }
}
