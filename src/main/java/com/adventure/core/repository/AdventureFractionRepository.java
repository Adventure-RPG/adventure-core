package com.adventure.core.repository;

import com.adventure.core.domain.AdventureFraction;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the AdventureFraction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdventureFractionRepository extends MongoRepository<AdventureFraction, String> {

}
