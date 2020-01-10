package com.adventure.core.repository;

import com.adventure.core.domain.AdventureAttributes;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the AdventureAttributes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdventureAttributesRepository extends MongoRepository<AdventureAttributes, String> {

}
