package tech.araopj.jokes.requests.entity;

import lombok.*;
import tech.araopj.jokes.entity.Category;
import tech.araopj.jokes.entity.Language;
import org.hibernate.Hibernate;
import jakarta.persistence.*;
import java.util.Objects;

@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "jokes_requests")
public final class JokeRequest {

    @Id
    @SequenceGenerator(
            name = "joke_request_sequence",
            sequenceName = "joke_request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "joke_request_sequence"
    )
    private Long id;
    private String joke;
    private Category category;
    private Language language;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JokeRequest that = (JokeRequest) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
