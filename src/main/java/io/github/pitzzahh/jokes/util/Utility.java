package io.github.pitzzahh.jokes.util;

import io.github.pitzzahh.jokes.repository.JokesRepository;
import java.util.concurrent.atomic.AtomicInteger;
import io.github.pitzzahh.jokes.entity.Category;
import io.github.pitzzahh.jokes.entity.Joke;
import org.json.simple.parser.*;
import lombok.SneakyThrows;
import java.io.FileReader;
import org.json.simple.*;
import java.util.Arrays;
import java.util.List;

public interface Utility {

    /**
     * Gets the jokes from a local JSON file
     * @return a list of jokes
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    static List<Joke> getJokes() {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/main/resources/static/jokes.json"));
        return jsonArray.stream()
                .map(JSONObject.class::cast)
                .map(o -> Joke.builder()
                        .joke((String) ((JSONObject) o).get("joke"))
                        .category(Category.valueOf((String) ((JSONObject) o).get("category")))
                        .build())
                .toList();
    }

    /**
     * Checks if a joke exists in the database
     * @param jokesRepository the jokes repository
     * @param joke the joke to check
     * @return true if the joke exists, false otherwise
     * @see JokesRepository
     * @see Joke
     */
    static boolean doesJokeExist(JokesRepository jokesRepository, String joke) {
        List<String[]> reduce = jokesRepository
                .findAll()
                .stream()
                .map(j -> j.getJoke().split("\\s"))
                .toList();

        AtomicInteger count = new AtomicInteger(0);

        String[] split = joke.split("\\s");
        reduce.forEach(str ->
                Arrays.stream(split)
                        .filter(s -> isPresent(str, s))
                        .forEachOrdered(s -> count.incrementAndGet())
        );
        return count.get() >= split.length;
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