package io.github.pitzzahh.jokes.service;

import io.github.pitzzahh.jokes.repository.JokesRepository;
import org.springframework.stereotype.Service;
import io.github.pitzzahh.jokes.entity.Joke;
import java.util.List;

@Service
public record JokesService(JokesRepository jokesRepository) {

    /**
     * Generates a random joke from the list of jokes
     * @return a random joke
     */
    public Joke generateRandomJoke() {
        List<Joke> jokes = jokesRepository.findAll();
        return jokes.isEmpty() ? new Joke() : jokes.get((int) (Math.random() * jokes.size()));
    }

}
