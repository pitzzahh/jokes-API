package tech.araopj.jokes.request.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tech.araopj.jokes.request.entity.JokeRequest;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface JokeRequestRepository extends JpaRepository<JokeRequest, Integer> {

    @Query("SELECT j FROM joke_requests j WHERE j.joke LIKE ?1 OR j.joke = ?1")
    Optional<JokeRequest> findJokeRequestByJoke(String joke);

    @Query("SELECT j FROM joke_requests j WHERE j.approved = false")
    Collection<JokeRequest> getJokeRequestByApprovedNot();

    @Query("SELECT j FROM joke_requests j WHERE j.approved = true")
    Collection<JokeRequest> getJokeRequestByApproved();

    @Modifying
    @Transactional
    @Query("DELETE FROM joke_requests j WHERE j.id = ?1")
    int deleteJokeRequestById(long id);

    @Modifying
    @Query("UPDATE joke_requests j SET j.approved = true WHERE j.id = ?1")
    void approveRequest(JokeRequest jokeRequest);
}
