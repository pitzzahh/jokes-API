package io.github.pitzzahh.jokes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.pitzzahh.jokes.entity.Joke;

@Repository
public interface JokesRepository extends JpaRepository<Joke, Integer> {

}
