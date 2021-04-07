package io.github.cgew85.nurl.model.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(indexes = @Index(columnList = "hashedUrl"))
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UrlData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @Column(unique = true)
    private String hashedUrl;

    private String key;

    private Long visits;

    private LocalDateTime createdDate;

    private LocalDateTime lastVisited;
}
