package io.github.pitzzahh.jokes.util;

import static io.github.pitzzahh.util.utilities.Util.isPresent;
import io.github.pitzzahh.jokes.repository.JokesRepository;
import java.util.concurrent.atomic.AtomicInteger;
import io.github.pitzzahh.jokes.entity.Category;
import io.github.pitzzahh.jokes.entity.Joke;
import org.json.simple.parser.*;
import java.io.IOException;
import java.io.FileReader;
import org.json.simple.*;
import java.util.Arrays;
import java.util.List;

public interface Utility {

    /**
     * Gets the jokes from a local JSON file
     * @return a list of jokes
     */
    @SuppressWarnings("unchecked")
    static List<Joke> getJokes() throws IOException, ParseException {
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader("src/main/resources/static/jokes.json"));
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
        AtomicInteger count = new AtomicInteger(0);
        String[] split = joke.split("\\s");
        jokesRepository
                .findAll()
                .stream()
                .map(j -> j.getJoke().split("\\s"))
                .forEach(str -> Arrays.stream(split)
                        .filter(s -> isPresent(str, s))
                        .forEachOrdered(s -> count.incrementAndGet())
                );
        return count.get() >= split.length;
    }

}