package tech.araopj.jokes.requests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import tech.araopj.jokes.requests.entity.JokeRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRequestRepository extends JpaRepository<JokeRequest, Long> {
}
