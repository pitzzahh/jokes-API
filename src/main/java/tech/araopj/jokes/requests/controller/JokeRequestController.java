package tech.araopj.jokes.requests.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import tech.araopj.jokes.requests.service.JokeRequestService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.araopj.jokes.requests.entity.JokeRequestBody;
import tech.araopj.jokes.service.JokesService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import static java.lang.String.format;

@RestController
@RequestMapping("v1/request")
public record JokeRequestController(
        JokesService jokesService,
        JokeRequestService jokeRequestService
) {

    @PostMapping("/add")
    public HttpEntity<String> addJoke(@Validated @RequestBody JokeRequestBody jokeRequestBody) throws ResponseStatusException {
        jokeRequestService
                .getAllJokeRequests()
                .stream()
                .filter(j -> j.getJoke().contains(jokeRequestBody.joke()) && j.getCategory().equals(jokeRequestBody.category()))
                .findAny()
                .ifPresent(j -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, format("Joke %s already exists", j.getJoke()));
                });

        jokesService.getAllJokes()
                .stream()
                .filter(j -> j.getJoke().contains(jokeRequestBody.joke()) && j.getCategory().equals(jokeRequestBody.category()))
                .findAny()
                .ifPresent(j -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, format("Joke %s already exists", j.getJoke()));
                });
        return jokeRequestService.submitJoke(jokeRequestBody);
    }

}
