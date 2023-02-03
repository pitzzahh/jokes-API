package tech.araopj.jokes.requests.controller;

import org.springframework.web.server.ResponseStatusException;
import tech.araopj.jokes.requests.service.JokeRequestService;
import org.springframework.validation.annotation.Validated;
import tech.araopj.jokes.requests.entity.JokeRequestBody;
import tech.araopj.jokes.requests.entity.JokeRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import static java.lang.String.format;
import java.util.Collection;

@RestController
@RequestMapping("v1/request")
public record JokeRequestController(JokeRequestService jokeRequestService) {

    @GetMapping("/all")
    public Collection<JokeRequest> getAllJokeRequests() {
        return jokeRequestService.getAllJokeRequests();
    }

    @PostMapping("/add")
    public HttpEntity<String> addJoke(@Validated @RequestBody JokeRequestBody jokeRequestBody) throws ResponseStatusException {
        jokeRequestService
                .doesJokeAlreadySubmitted(jokeRequestBody.joke())
                .ifPresent(j -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, format("Joke %s already exists and submitted", j.getJoke()));
                });

        return jokeRequestService.submitJoke(jokeRequestBody);
    }

}
