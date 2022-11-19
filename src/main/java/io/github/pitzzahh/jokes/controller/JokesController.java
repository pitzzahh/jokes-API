package io.github.pitzzahh.jokes.controller;

import io.github.pitzzahh.util.utilities.classes.enums.Status;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import io.github.pitzzahh.jokes.service.JokesService;
import io.github.pitzzahh.jokes.entity.Joke;

@RestController
@RequestMapping("api/v1/jokes")
public record JokesController(JokesService jokesService) {

    @RequestMapping("/random")
    public Joke getRandomJoke() {
        return jokesService.generateRandomJoke();
    }

    @PostMapping("/add")
    public Status addJoke(@Validated @RequestBody Joke joke) {
        return jokesService.addJoke(joke);
    }

    @RequestMapping("/save")
    public Status saveAll() {
        return jokesService.saveAll();
    }

}
