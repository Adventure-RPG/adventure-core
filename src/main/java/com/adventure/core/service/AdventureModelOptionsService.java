package com.adventure.core.service;

import com.adventure.core.service.dto.AdventureModelOptionsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.adventure.core.domain.AdventureModelOptions}.
 */
public interface AdventureModelOptionsService {

    /**
     * Save a adventureModelOptions.
     *
     * @param adventureModelOptionsDTO the entity to save.
     * @return the persisted entity.
     */
    AdventureModelOptionsDTO save(AdventureModelOptionsDTO adventureModelOptionsDTO);

    /**
     * Get all the adventureModelOptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureModelOptionsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" adventureModelOptions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdventureModelOptionsDTO> findOne(String id);

    /**
     * Delete the "id" adventureModelOptions.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Search for the adventureModelOptions corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureModelOptionsDTO> search(String query, Pageable pageable);
}
