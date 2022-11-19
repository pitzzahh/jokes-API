package io.github.pitzzahh.jokes.repository;

import org.springframework.stereotype.Repository;
import io.github.pitzzahh.jokes.model.Joke;
import java.util.List;

@Repository
public interface JokesRepository {

    /**
     * Gets a list of jokes
     * @return a {@code List<Joke>}.
     * @see Joke
     */
    List<Joke> getJokes();

}
