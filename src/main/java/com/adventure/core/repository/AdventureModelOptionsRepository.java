package com.adventure.core.repository;

import com.adventure.core.domain.AdventureModelOptions;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the AdventureModelOptions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdventureModelOptionsRepository extends MongoRepository<AdventureModelOptions, String> {

}
