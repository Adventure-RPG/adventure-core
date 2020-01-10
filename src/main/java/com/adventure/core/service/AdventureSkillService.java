package com.adventure.core.service;

import com.adventure.core.service.dto.AdventureSkillDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.adventure.core.domain.AdventureSkill}.
 */
public interface AdventureSkillService {

    /**
     * Save a adventureSkill.
     *
     * @param adventureSkillDTO the entity to save.
     * @return the persisted entity.
     */
    AdventureSkillDTO save(AdventureSkillDTO adventureSkillDTO);

    /**
     * Get all the adventureSkills.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureSkillDTO> findAll(Pageable pageable);


    /**
     * Get the "id" adventureSkill.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdventureSkillDTO> findOne(String id);

    /**
     * Delete the "id" adventureSkill.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Search for the adventureSkill corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdventureSkillDTO> search(String query, Pageable pageable);
}
