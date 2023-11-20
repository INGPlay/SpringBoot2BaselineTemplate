package baseline.version2.springboot.example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseVO {
    private Long id;
    private String content;
}
