package com.adventure.core.service;

import com.adventure.core.service.dto.AdventureRaceOptionsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.adventure.core.domain.AdventureRaceOptions}.
 */
public interface AdventureRaceOptionsService {

    /**
     * Save a adventureRaceOptions.
     *
     * @param adventureRaceOptionsDTO the entity to save.
     * @return the persisted entity.
     */
    AdventureRaceOptionsDTO save(AdventureRaceOptionsDTO adventureRaceOptionsDTO);

    /**
     * Get all the adventureRaceOptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureRaceOptionsDTO> findAll(Pageable pageable);

    /**
     * Get all the adventureRaceOptions with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<AdventureRaceOptionsDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" adventureRaceOptions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdventureRaceOptionsDTO> findOne(String id);

    /**
     * Delete the "id" adventureRaceOptions.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Search for the adventureRaceOptions corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureRaceOptionsDTO> search(String query, Pageable pageable);
}
