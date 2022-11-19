package io.github.pitzzahh.jokes.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.Hibernate;
import javax.persistence.Id;
import java.util.Objects;
import lombok.*;
@Entity(name = "jokes")
@Table
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Joke {

    @Id
    @GeneratedValue
    private Integer id;
    private String joke;
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Joke joke = (Joke) o;
        return id != null && Objects.equals(id, joke.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
