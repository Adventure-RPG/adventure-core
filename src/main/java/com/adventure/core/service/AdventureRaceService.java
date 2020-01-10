package com.adventure.core.service;

import com.adventure.core.service.dto.AdventureRaceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.adventure.core.domain.AdventureRace}.
 */
public interface AdventureRaceService {

    /**
     * Save a adventureRace.
     *
     * @param adventureRaceDTO the entity to save.
     * @return the persisted entity.
     */
    AdventureRaceDTO save(AdventureRaceDTO adventureRaceDTO);

    /**
     * Get all the adventureRaces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureRaceDTO> findAll(Pageable pageable);

    /**
     * Get all the adventureRaces with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<AdventureRaceDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" adventureRace.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdventureRaceDTO> findOne(String id);

    /**
     * Delete the "id" adventureRace.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Search for the adventureRace corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureRaceDTO> search(String query, Pageable pageable);
}
