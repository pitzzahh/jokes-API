package tech.araopj.jokes.requests.service;

import tech.araopj.jokes.requests.repository.JokeRequestRepository;
import tech.araopj.jokes.requests.entity.JokeRequest;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;

@Service
public record JokeRequestService(JokeRequestRepository repository) {

    public HttpEntity<String> submitJoke(JokeRequest jokeRequest) {
        repository.save(jokeRequest);
        return new HttpEntity<>("Joke submitted successfully");
    }

}
