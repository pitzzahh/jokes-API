package tech.araopj.jokes.requests.service;

import tech.araopj.jokes.requests.repository.JokeRequestRepository;
import tech.araopj.jokes.requests.entity.JokeRequest;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import java.util.Collection;

@Service
public record JokeRequestService(JokeRequestRepository repository) {

    public Collection<JokeRequest> getAllJokeRequests() {
        return repository.findAll();
    }

    public HttpEntity<String> submitJoke(JokeRequest jokeRequest) {
        repository.save(jokeRequest);
        return new HttpEntity<>("Joke submitted successfully");
    }

    public HttpEntity<String> deleteJoke(JokeRequest jokeRequest) {
        return new HttpEntity<>(
                repository.deleteJokeRequestById(jokeRequest.getId()) == 1 ? "Joke deleted successfully" : "Joke not found"
        );
    }
}
