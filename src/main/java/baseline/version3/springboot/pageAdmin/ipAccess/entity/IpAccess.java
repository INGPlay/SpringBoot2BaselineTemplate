package baseline.version3.springboot.pageAdmin.ipAccess.entity;

import baseline.version3.springboot.common.domain.subType.ApplyStatus;
import baseline.version3.springboot.common.entity.base.DateTimeBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "IpAccess")
@Getter @Setter
public class IpAccess extends DateTimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipAccessId", nullable = false)
    private Long ipAccessId;

    @Pattern(regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)$")
    @Column(nullable = false, unique = true)
    private String ipAddress;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplyStatus applyStatus;

    @Column
    @Size(max = 30)
    private String ipAccessDescription;

}
