package com.adventure.core.repository;

import com.adventure.core.domain.AdventureModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the AdventureModel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdventureModelRepository extends MongoRepository<AdventureModel, String> {

}
