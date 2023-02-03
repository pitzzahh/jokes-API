package tech.araopj.jokes.request.service;

import tech.araopj.jokes.request.repository.JokeRequestRepository;
import tech.araopj.jokes.request.entity.JokeRequestBody;
import tech.araopj.jokes.request.entity.JokeRequest;
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
