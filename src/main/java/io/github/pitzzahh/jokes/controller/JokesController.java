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

package io.github.pitzzahh.jokes.controller;

import io.github.pitzzahh.util.utilities.classes.enums.Status;
import org.springframework.validation.annotation.Validated;
import io.github.pitzzahh.jokes.service.JokesService;
import org.springframework.web.bind.annotation.*;
import org.json.simple.parser.ParseException;
import io.github.pitzzahh.jokes.entity.Joke;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/jokes")
public record JokesController(JokesService jokesService) {

    @RequestMapping("/random")
    public Joke getRandomJoke() {
        return jokesService.generateRandomJoke();
    }

    /**
     * Gets a joke by its category
     * @param category the category of the joke
     * @return the joke
     */
    @RequestMapping(
            value = "/random/{category}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public Joke getJokeByCategory(@PathVariable String category) {
        return jokesService.getJokeByCategory(category);
    }

    @PostMapping("/add")
    public Status addJoke(@Validated @RequestBody Joke joke) {
        return jokesService.addJoke(joke);
    }

    @PostMapping("/save")
    public Status saveAll() throws IOException, ParseException {
        return jokesService.saveAll();
    }

}
