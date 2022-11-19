package io.github.pitzzahh.jokes.service;

import io.github.pitzzahh.jokes.repository.JokesRepository;
import io.github.pitzzahh.jokes.util.Utility;
import io.github.pitzzahh.util.utilities.classes.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.github.pitzzahh.jokes.entity.Joke;
import java.util.List;

@Service
@Slf4j
public record JokesService(JokesRepository jokesRepository) {

    /**
     * Generates a random joke from the list of jokes
     * @return a random joke
     */
    public Joke generateRandomJoke() {
        List<Joke> jokes = jokesRepository.findAll();
        log.debug("Generating random joke from {} jokes", jokes.size());
        return jokes.isEmpty() ? new Joke() : jokes.get((int) (Math.random() * jokes.size()));
    }

    public Status addJoke(Joke joke) {
        if (Utility.doesJokeExist(jokesRepository, joke.getJoke())) return Status.ERROR;
        jokesRepository.save(joke);
        return Status.SUCCESS;
    }

    // TODO: remove after using
    public Status saveAll() {
        jokesRepository.saveAll(Utility.getJokes().get());
        return Status.SUCCESS;
    }
}
