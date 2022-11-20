package io.github.pitzzahh.jokes.service;

import io.github.pitzzahh.util.utilities.classes.enums.Status;
import io.github.pitzzahh.jokes.repository.JokesRepository;
import org.springframework.stereotype.Service;
import org.json.simple.parser.ParseException;
import io.github.pitzzahh.jokes.util.Utility;
import io.github.pitzzahh.jokes.entity.Joke;
import java.io.IOException;
import java.util.List;

/**
 * The jokes service
 * @param jokesRepository the jokes repository
 * @see JokesRepository
 * @see Joke
 * @see Utility
 * @see Status
 * @since 19
 */
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

    /**
     * Adds a joke to the database, if it doesn't exist
     * @param joke the joke to add
     * @return the status of the operation
     * @see Status
     * @see Joke
     * @see JokesRepository
     * @see Utility
     * @since 19
     */
    public Status addJoke(Joke joke) {
        if (Utility.doesJokeExist(jokesRepository, joke.getJoke())) return Status.ERROR;
        jokesRepository.save(joke);
        return Status.SUCCESS;
    }

    /**
     * Saves all the jokes in the database from the local JSON file
     * @return the status of the operation
     * @see Status
     * @see Joke
     * @see JokesRepository
     * @see Utility#getJokes()
     */
    // TODO: remove after using
    public Status saveAll() throws IOException, ParseException {
        jokesRepository.saveAll(Utility.getJokes());
        return Status.SUCCESS;
    }

}
