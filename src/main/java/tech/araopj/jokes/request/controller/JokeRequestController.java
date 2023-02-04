package tech.araopj.jokes.request.controller;

import org.springframework.web.server.ResponseStatusException;
import tech.araopj.jokes.request.service.JokeRequestService;
import org.springframework.validation.annotation.Validated;
import tech.araopj.jokes.request.entity.JokeRequestBody;
import tech.araopj.jokes.request.entity.JokeRequest;
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
                .ifPresent(joke -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            format("Joke %s already exists and submitted", joke.getJoke())
                    );
                });
        return jokeRequestService.submitJoke(jokeRequestBody);
    }

    @PostMapping("/approve")
    public HttpEntity<String> approveJoke(@RequestParam(name = "joke_id") int id) {
        jokeRequestService.approveJoke(id);
        return new HttpEntity<>("Joke approved successfully");
    }

}
