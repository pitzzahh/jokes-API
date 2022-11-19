package io.github.pitzzahh.jokes.repository;

import org.springframework.stereotype.Component;
import io.github.pitzzahh.jokes.model.Joke;
import io.github.pitzzahh.jokes.util.Util;
import java.util.List;

@Component
public class JokesRepositoryImp implements JokesRepository {

    @Override
    public List<Joke> getJokes() {
        return Util.getJokes().orElse(List.of());
    }
}
