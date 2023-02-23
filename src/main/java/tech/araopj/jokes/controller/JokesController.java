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

package tech.araopj.jokes.controller;

import static io.github.pitzzahh.util.utilities.SecurityUtil.decrypt;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;
import tech.araopj.jokes.service.JokesService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import tech.araopj.jokes.entity.Language;
import tech.araopj.jokes.entity.Category;
import static java.lang.String.format;
import tech.araopj.jokes.entity.Joke;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("v1")
public record JokesController(JokesService jokesService) {

    @GetMapping("/save-all")
    public HttpEntity<String> saveAll(@RequestParam("key") String key) {
        return key.equals(decrypt("IVA0c3NXMHJkIQ==")) ? new HttpEntity<>(jokesService.saveAll().toString()) : new HttpEntity<>(HttpStatus.FORBIDDEN.name());
    }

    @GetMapping("/random")
    public HttpEntity<Joke> getRandomJoke(@RequestParam(required = false, name = "category") String category,
                              @RequestParam(required = false, name = "language") String language) throws ResponseStatusException {
        if (category != null && language != null) {
            validateCategory(category);
            validateLanguage(language);
            return new HttpEntity<>(jokesService
                    .getRandomJokeByCategoryAndLanguage(Category.valueOf(category.toUpperCase()), Language.valueOf(language.toUpperCase()))
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            format("No jokes found for category %s with language %s", category.toUpperCase(), language.toUpperCase())
                    )));
        }
        else if (category != null) return new HttpEntity<>(getRandomJokeByCategory(category));
        else if (language != null) return new HttpEntity<>(getRandomJokeByLanguage(language));
        else {
            jokesService
                    .getAllJokes()
                    .stream()
                    .findAny()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No jokes found"));
            return new HttpEntity<>(jokesService.getAllJokes()
                    .stream()
                    .skip(new Random().nextInt(jokesService.getAllJokes().size()))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No jokes found")));
        }
    }

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/categories")
    public HttpEntity<Collection<Category>> getCategories() {
        return new HttpEntity<>(List.of(Category.values()));
    }

    @RequestMapping("/languages")
    public HttpEntity<Collection<Language>> getLanguages() {
        return new HttpEntity<>(List.of(Language.values()));
    }

    private Joke getRandomJokeByCategory(String category) {
        validateCategory(category);
        return jokesService
                .getRandomJokeByCategory(Category.valueOf(category.toUpperCase()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, format("No jokes found for category %s", category.toUpperCase())
                ));
    }

    private void validateCategory(String category) {
        boolean isValid = Arrays.stream(Category.values()).anyMatch(e -> e.toString().equals(category.toUpperCase()));
        if (!isValid) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, format("Category %s does not exist", category.toUpperCase())
        );
    }

    private Joke getRandomJokeByLanguage(String language) {
        validateLanguage(language);
        return jokesService
                .getRandomJokeByLanguage(Language.valueOf(language.toUpperCase()))
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                format("No jokes found for joke with language %s", language.toUpperCase())
                        )
                );
    }

    private void validateLanguage(String language) {
        boolean isValid = Arrays.stream(Language.values())
                .anyMatch(e -> e.toString().equals(language.toUpperCase()));
        if (!isValid) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, format("Language %s does not exist, use: [%s]", language.toUpperCase(), Arrays.toString(Language.values())
        ));
    }

}
