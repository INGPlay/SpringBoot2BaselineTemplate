package baseline.version3.springboot.project.example.domain;

import lombok.Getter;
import lombok.Setter;

public class ExampleRequest {

    @Getter @Setter
    public static class RequestInsert {
        private String content;
    }

    @Getter @Setter
    public static class RequestUpdate {
        private Long id;
        private String content;
    }

    @Getter @Setter
    public static class RequestDelete {
        private Long id;
    }

    @Getter @Setter
    public static class RequestDynamicQuery {
        private Long id;
        private String content;
    }

    @Getter @Setter
    public static class RequestDynamicQueryOne {
        private Long id;
        private String content;
    }
}
