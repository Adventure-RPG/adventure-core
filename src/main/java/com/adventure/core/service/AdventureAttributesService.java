package com.adventure.core.service;

import com.adventure.core.service.dto.AdventureAttributesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.adventure.core.domain.AdventureAttributes}.
 */
public interface AdventureAttributesService {

    /**
     * Save a adventureAttributes.
     *
     * @param adventureAttributesDTO the entity to save.
     * @return the persisted entity.
     */
    AdventureAttributesDTO save(AdventureAttributesDTO adventureAttributesDTO);

    /**
     * Get all the adventureAttributes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureAttributesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" adventureAttributes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdventureAttributesDTO> findOne(String id);

    /**
     * Delete the "id" adventureAttributes.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Search for the adventureAttributes corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureAttributesDTO> search(String query, Pageable pageable);
}
