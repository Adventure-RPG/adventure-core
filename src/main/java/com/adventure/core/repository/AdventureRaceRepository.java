package com.adventure.core.repository;

import com.adventure.core.domain.AdventureRace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the AdventureRace entity.
 */
@Repository
public interface AdventureRaceRepository extends MongoRepository<AdventureRace, String> {

    @Query("{}")
    Page<AdventureRace> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<AdventureRace> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<AdventureRace> findOneWithEagerRelationships(String id);

}
