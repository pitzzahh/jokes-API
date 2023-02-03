package tech.araopj.jokes.requests.entity;

import tech.araopj.jokes.entity.Category;
import tech.araopj.jokes.entity.Language;
import org.hibernate.Hibernate;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.*;

@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "joke_request")
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
    private Integer id;
    private String joke;
    private Category category;
    private Language lang;

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
