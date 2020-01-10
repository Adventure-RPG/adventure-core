package com.adventure.core.repository;

import com.adventure.core.domain.AdventureRaceOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the AdventureRaceOptions entity.
 */
@Repository
public interface AdventureRaceOptionsRepository extends MongoRepository<AdventureRaceOptions, String> {

    @Query("{}")
    Page<AdventureRaceOptions> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<AdventureRaceOptions> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<AdventureRaceOptions> findOneWithEagerRelationships(String id);

}
