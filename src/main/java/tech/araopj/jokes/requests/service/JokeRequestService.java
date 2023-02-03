package tech.araopj.jokes.requests.service;

import tech.araopj.jokes.requests.repository.JokeRequestRepository;
import tech.araopj.jokes.requests.entity.JokeRequestBody;
import tech.araopj.jokes.requests.entity.JokeRequest;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import java.util.Collection;

@Service
public record JokeRequestService(JokeRequestRepository repository) {

    public Collection<JokeRequest> getAllJokeRequests() {
        return repository.findAll();
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
