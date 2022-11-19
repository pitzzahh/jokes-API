package io.github.pitzzahh.jokes.util;

import io.github.pitzzahh.jokes.repository.JokesRepository;
import java.util.concurrent.atomic.AtomicInteger;
import io.github.pitzzahh.jokes.entity.Category;
import io.github.pitzzahh.jokes.entity.Joke;
import org.json.simple.parser.*;
import java.util.ArrayList;
import java.util.Optional;
import java.io.FileReader;
import org.json.simple.*;
import java.util.Arrays;
import java.util.List;

public interface Utility {

    static Optional<List<Joke>> getJokes() {
        JSONParser parser = new JSONParser();
        List<Joke> jokes = new ArrayList<>();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("src/main/resources/static/jokes.json"));

            for (Object o : a) {
                JSONObject joke = (JSONObject) o;
                jokes.add(
                        Joke.builder()
                                .joke((String) joke.get("joke"))
                                .category(Category.valueOf((String) joke.get("category")))
                                .build()
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return Optional.of(jokes);
    }

    static boolean doesJokeExist(JokesRepository jokesRepository, String joke) {
        Optional<String[]> reduce = jokesRepository
                .findAll()
                .stream()
                .map(j -> j.getJoke().split("\\s"))
                .reduce((j1, j2) -> {
                    for (String s : j1) {
                        for (String s1 : j2) {
                            if (s.equalsIgnoreCase(s1)) return j1;
                        }
                    }
                    return j1;
                });
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger count = new AtomicInteger(0);
        reduce.ifPresent(str -> {
            if (isPresent(str, joke.split("\\s")[atomicInteger.getAndIncrement()])) count.incrementAndGet();
        });
        return count.get() >= joke.split("\\s").length;
    }
    /**
     * Method that searches an array, returns true if the value is present, otherwise false.
     * @param arr the array that extends the {@code Number} class.
     * @param whatToFind the number to find in the array.
     * @return {@code true} if {@code whatToFind} is present in the array.
     * @param <T> the type that the {@code arr} and {@code whatToFind}
     * @see Number
     */
    private static <T> boolean isPresent(T[] arr, T whatToFind) {
        return Arrays.asList(arr).contains(whatToFind);
    }
}