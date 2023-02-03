package tech.araopj.jokes.requests.service;

import tech.araopj.jokes.entity.Joke;
import tech.araopj.jokes.requests.repository.JokeRequestRepository;
import tech.araopj.jokes.requests.entity.JokeRequestBody;
import tech.araopj.jokes.requests.entity.JokeRequest;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import java.util.Collection;
import java.util.Optional;

@Service
public record JokeRequestService(JokeRequestRepository repository) {

    public Collection<JokeRequest> getAllJokeRequests() {
        return repository.findAll();
    }

    public Optional<JokeRequest> doesJokeAlreadySubmitted(String joke) {
        return repository.findJokeRequestByJoke(joke);
    }

    public HttpEntity<String> submitJoke(JokeRequestBody jokeRequestBody) {
        repository.save(JokeRequest.builder()
                .joke(jokeRequestBody.joke())
                .category(jokeRequestBody.category())
                .lang(jokeRequestBody.language())
                .build()
        );
        return new HttpEntity<>("Joke submitted successfully");
    }

}
