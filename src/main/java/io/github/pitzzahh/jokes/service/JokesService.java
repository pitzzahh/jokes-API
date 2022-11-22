/*
 * MIT License
 *
 * Copyright (c) 2022 pitzzahh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.pitzzahh.jokes.service;

import io.github.pitzzahh.jokes.exception.CategoryNotFoundException;
import io.github.pitzzahh.util.utilities.classes.enums.Status;
import io.github.pitzzahh.jokes.repository.JokesRepository;
import io.github.pitzzahh.jokes.entity.Category;
import org.springframework.stereotype.Service;
import org.json.simple.parser.ParseException;
import io.github.pitzzahh.jokes.util.Utility;
import io.github.pitzzahh.jokes.entity.Joke;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public record JokesService(JokesRepository jokesRepository) {

    /**
     * Generates a random joke from the list of jokes
     * @return a random joke
     */
    public Joke generateRandomJoke() {
        return Utility.pickRandomJoke(jokesRepository);
    }

    /**
     * Gets a joke by category
     * @param category the category
     * @return a joke
     */
    public Joke getJokeByCategory(String category) {
        return jokesRepository.findAll()
                .stream().filter(joke -> joke.getCategory().equals(Category.valueOf(category)))
                .findAny()
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
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
        Utility.getJokes()
                .forEach(this::addJoke);
        return Status.SUCCESS;
    }

}
