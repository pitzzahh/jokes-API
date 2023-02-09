package tech.araopj.jokes.submit.controller;

import static io.github.pitzzahh.util.utilities.SecurityUtil.decrypt;
import io.github.pitzzahh.util.utilities.validation.Validator;
import org.springframework.web.server.ResponseStatusException;
import tech.araopj.jokes.submit.service.JokeRequestService;
import org.springframework.validation.annotation.Validated;
import tech.araopj.jokes.submit.entity.JokeRequestBody;
import tech.araopj.jokes.submit.entity.JokeRequest;
import org.springframework.web.bind.annotation.*;
import tech.araopj.jokes.service.JokesService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import static java.lang.String.format;
import tech.araopj.jokes.entity.Joke;
import java.util.Collection;

@RestController
@RequestMapping("v1/submit")
public record JokeRequestController(
        JokesService jokesService,
        JokeRequestService jokeRequestService
) {

    @GetMapping("/all")
    public Collection<JokeRequest> getAllJokeRequests() {
        return jokeRequestService.getAllJokeRequests();
    }

    @PostMapping
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

    @RequestMapping("/approve")
    public HttpEntity<String> approveJoke(
            @Validated
            @RequestParam("key") String key,
            @Validated
            @RequestParam(name = "joke_id") String id
    ) {
        if (!Validator.isWholeNumber().test(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid joke id, must be a number");
        }
        if (!key.equals(decrypt("IVA0c3NXMHJkIQ=="))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid key");
        }
        jokeRequestService.approveJoke(Integer.parseInt(id));
        return new HttpEntity<>("Joke approved successfully");
    }

    @RequestMapping("/transfer-jokes")
    public HttpEntity<String> transferToJokes(@Validated @RequestParam(name = "key") String key) {
        if (!key.equals(decrypt("IVA0c3NXMHJkIQ=="))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid key");
        }
        jokeRequestService
                .getAllApprovedJokeRequests()
                .stream()
                .map(j -> Joke.builder()
                        .joke(j.getJoke())
                        .category(j.getCategory())
                        .language(j.getLanguage())
                        .build())
                .forEach(jokesService::addJoke);
        return new HttpEntity<>("Jokes transferred successfully");
    }

    @RequestMapping("/delete")
    public HttpEntity<String> deleteJokeRequest(
            @Validated
            @RequestParam("key") String key,
            @RequestParam(name = "joke_id") String id
    ) {
        if (!Validator.isWholeNumber().test(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid joke id, must be a number");
        }
        if (!key.equals(decrypt("IVA0c3NXMHJkIQ=="))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid key");
        }
        jokeRequestService.deleteJokeRequestById(Integer.parseInt(id));
        return new HttpEntity<>("Joke deleted successfully");
    }

}