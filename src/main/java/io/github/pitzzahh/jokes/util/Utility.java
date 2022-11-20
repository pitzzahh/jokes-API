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