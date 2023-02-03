package tech.araopj.jokes.requests.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import tech.araopj.jokes.requests.entity.JokeRequestBody;
import org.springframework.data.jpa.repository.Query;
import tech.araopj.jokes.requests.entity.JokeRequest;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JokeRequestRepository extends JpaRepository<JokeRequest, Integer> {


    @Query("SELECT j FROM joke_requests j WHERE j.joke LIKE ?1")
    Optional<JokeRequestBody> findJokeRequestByJoke(String joke);

    @Transactional
    @Modifying
    @Query("DELETE FROM joke_requests j WHERE j.id = ?1")
    int deleteJokeRequestById(long id);

}
