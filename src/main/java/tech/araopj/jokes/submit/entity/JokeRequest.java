package tech.araopj.jokes.submit.entity;

import tech.araopj.jokes.entity.Category;
import jakarta.persistence.GeneratedValue;
import tech.araopj.jokes.entity.Language;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "joke_requests")
public class JokeRequest  {

    @Id
    @GeneratedValue
    private Integer id;
    private String joke;
    private Category category;
    private Language language;
    private Boolean approved;

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
