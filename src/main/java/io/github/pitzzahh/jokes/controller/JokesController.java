package io.github.pitzzahh.jokes.controller;

import io.github.pitzzahh.jokes.entity.Joke;
import io.github.pitzzahh.jokes.service.JokesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/jokes")
public record JokesController(JokesService jokesService) {

    @RequestMapping("/random")
    public Joke getRandomJoke() {
        return jokesService.generateRandomJoke();
    }

}
