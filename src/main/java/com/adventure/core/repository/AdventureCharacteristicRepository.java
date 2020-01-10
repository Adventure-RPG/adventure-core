package com.adventure.core.repository;

import com.adventure.core.domain.AdventureCharacteristic;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the AdventureCharacteristic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdventureCharacteristicRepository extends MongoRepository<AdventureCharacteristic, String> {

}
