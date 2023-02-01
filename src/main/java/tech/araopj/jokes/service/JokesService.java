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

package tech.araopj.jokes.service;

import io.github.pitzzahh.util.utilities.classes.enums.Status;
import tech.araopj.jokes.repository.JokesRepository;
import tech.araopj.jokes.entity.Language;
import tech.araopj.jokes.entity.Category;
import org.springframework.stereotype.Service;
import tech.araopj.jokes.util.Utility;
import tech.araopj.jokes.entity.Joke;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;
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

    public Collection<Joke> getAllJokes() {
        return jokesRepository.findAll();
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
    public HttpStatus addJoke(Joke joke) {
        if (Utility.doesJokeExist(jokesRepository, joke.getJoke())) return HttpStatus.CONFLICT;
        jokesRepository.save(joke);
        return HttpStatus.OK;
    }

    /**
     * Saves all the jokes in the database from the local JSON file
     * @return the status of the operation
     * @see HttpStatus
     * @see Joke
     * @see JokesRepository
     * @see Utility#getJokes()
     */
    public HttpStatus saveAll() {
        Utility.getJokes()
                .forEach(this::addJoke);
        return HttpStatus.OK;
    }

    public Optional<Joke> getRandomJokeByCategory(Category category) {
        List<Joke> jokeByCategory = jokesRepository.findJokeByCategory(category);
        return jokeByCategory.isEmpty() ? Optional.empty() : Optional.ofNullable(jokeByCategory.get(new Random().nextInt(jokeByCategory.size())));
    }

    public Optional<Joke> getRandomJokeByLanguage(Language lang) {
        List<Joke> jokeByLanguage = jokesRepository.findJokeByLanguage(lang);
        return jokeByLanguage.isEmpty() ? Optional.empty() : Optional.ofNullable(jokeByLanguage.get(new Random().nextInt(jokeByLanguage.size())));
    }

    public Optional<Joke> getRandomJokeByCategoryAndLanguage(Category category, Language language) {
        List<Joke> jokeByCategoryAndLang = jokesRepository.findJokeByCategoryAndLang(category, language);
        return jokeByCategoryAndLang.isEmpty() ? Optional.empty() :
                Optional.ofNullable(jokeByCategoryAndLang.get(new Random().nextInt(jokeByCategoryAndLang.size())));
    }
}
