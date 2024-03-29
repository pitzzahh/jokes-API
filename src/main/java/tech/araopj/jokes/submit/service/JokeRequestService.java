package tech.araopj.jokes.submit.service;

import tech.araopj.jokes.submit.repository.JokeRequestRepository;
import tech.araopj.jokes.submit.entity.JokeRequestBody;
import tech.araopj.jokes.repository.JokesRepository;
import tech.araopj.jokes.submit.entity.JokeRequest;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import java.util.Collection;
import java.util.Optional;

@Service
public record JokeRequestService(
        JokesRepository jokesRepository,
        JokeRequestRepository jokeRequestRepository
) {

    public Collection<JokeRequest> getAllJokeRequests() {
        return jokeRequestRepository.getJokeRequestByApprovedNot();
    }

    public Collection<JokeRequest> getAllApprovedJokeRequests() {
        return jokeRequestRepository.getJokeRequestByApproved();
    }

    public Optional<JokeRequest> doesJokeAlreadySubmitted(String joke) {
        return jokeRequestRepository.findJokeRequestByJoke(joke);
    }

    public void deleteJokeRequestById(int id) {
        jokeRequestRepository.deleteJokeRequestById(id);
    }

    public HttpEntity<String> submitJoke(JokeRequestBody jokeRequestBody) {
        jokeRequestRepository.save(JokeRequest.builder()
                .joke(jokeRequestBody.joke())
                .category(jokeRequestBody.category())
                .language(jokeRequestBody.language())
                .approved(false)
                .build()
        );
        return new HttpEntity<>("Joke submitted successfully");
    }

    public void approveJoke(int id) {
        jokeRequestRepository
                .findById(id)
                .ifPresent(jokeRequestRepository::approveRequest);
    }
}
