package io.github.pitzzahh.jokes.util;

import io.github.pitzzahh.jokes.model.Joke;
import org.json.simple.parser.*;
import java.util.ArrayList;
import java.util.Optional;
import java.io.FileReader;
import org.json.simple.*;
import java.util.List;

public interface Util {

    @SuppressWarnings("unchecked")
    static Optional<List<Joke>> getJokes() {
        JSONParser parser = new JSONParser();
        List<Joke> jokes = new ArrayList<>();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("src/main/resources/static/jokes.json"));


            for (Object o : a) {

                JSONObject joke = (JSONObject) o;
                JSONArray categories = (JSONArray) joke.get("categories");

                String jokeText = (String) joke.get("joke");

                jokes.add(
                        Joke.builder()
                                .categories(categories)
                                .joke(jokeText)
                                .build()
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return Optional.of(jokes);
    }
}