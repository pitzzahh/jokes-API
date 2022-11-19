package io.github.pitzzahh.jokes.util;

import io.github.pitzzahh.jokes.entity.Category;
import io.github.pitzzahh.jokes.entity.Joke;
import org.json.simple.parser.*;
import java.util.ArrayList;
import java.util.Optional;
import java.io.FileReader;
import org.json.simple.*;
import java.util.List;

public interface Util {

    static Optional<List<Joke>> getJokes() {
        JSONParser parser = new JSONParser();
        List<Joke> jokes = new ArrayList<>();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("src/main/resources/static/jokes.json"));

            for (Object o : a) {
                JSONObject joke = (JSONObject) o;
                jokes.add(
                        Joke.builder()
                                .id(Integer.parseInt((String) joke.get("id")))
                                .category(Category.valueOf((String) joke.get("category")))
                                .joke((String) joke.get("joke"))
                                .build()
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return Optional.of(jokes);
    }
}