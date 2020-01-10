package com.adventure.core.repository;

import com.adventure.core.domain.AdventureScript;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the AdventureScript entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdventureScriptRepository extends MongoRepository<AdventureScript, String> {

}
