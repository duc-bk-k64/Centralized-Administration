package hust.admin.project.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String ip_address;
    @Column
    private String action;
    @Column
    private Instant time_log;

}
