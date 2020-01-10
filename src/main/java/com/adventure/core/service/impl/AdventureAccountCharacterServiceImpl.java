package com.adventure.core.service.impl;

import com.adventure.core.service.AdventureAccountCharacterService;
import com.adventure.core.domain.AdventureAccountCharacter;
import com.adventure.core.repository.AdventureAccountCharacterRepository;
import com.adventure.core.repository.search.AdventureAccountCharacterSearchRepository;
import com.adventure.core.service.dto.AdventureAccountCharacterDTO;
import com.adventure.core.service.mapper.AdventureAccountCharacterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link AdventureAccountCharacter}.
 */
@Service
public class AdventureAccountCharacterServiceImpl implements AdventureAccountCharacterService {

    private final Logger log = LoggerFactory.getLogger(AdventureAccountCharacterServiceImpl.class);

    private final AdventureAccountCharacterRepository adventureAccountCharacterRepository;

    private final AdventureAccountCharacterMapper adventureAccountCharacterMapper;

    private final AdventureAccountCharacterSearchRepository adventureAccountCharacterSearchRepository;

    public AdventureAccountCharacterServiceImpl(AdventureAccountCharacterRepository adventureAccountCharacterRepository, AdventureAccountCharacterMapper adventureAccountCharacterMapper, AdventureAccountCharacterSearchRepository adventureAccountCharacterSearchRepository) {
        this.adventureAccountCharacterRepository = adventureAccountCharacterRepository;
        this.adventureAccountCharacterMapper = adventureAccountCharacterMapper;
        this.adventureAccountCharacterSearchRepository = adventureAccountCharacterSearchRepository;
    }

    /**
     * Save a adventureAccountCharacter.
     *
     * @param adventureAccountCharacterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdventureAccountCharacterDTO save(AdventureAccountCharacterDTO adventureAccountCharacterDTO) {
        log.debug("Request to save AdventureAccountCharacter : {}", adventureAccountCharacterDTO);
        AdventureAccountCharacter adventureAccountCharacter = adventureAccountCharacterMapper.toEntity(adventureAccountCharacterDTO);
        adventureAccountCharacter = adventureAccountCharacterRepository.save(adventureAccountCharacter);
        AdventureAccountCharacterDTO result = adventureAccountCharacterMapper.toDto(adventureAccountCharacter);
        adventureAccountCharacterSearchRepository.save(adventureAccountCharacter);
        return result;
    }

    /**
     * Get all the adventureAccountCharacters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureAccountCharacterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdventureAccountCharacters");
        return adventureAccountCharacterRepository.findAll(pageable)
            .map(adventureAccountCharacterMapper::toDto);
    }

    /**
     * Get all the adventureAccountCharacters with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<AdventureAccountCharacterDTO> findAllWithEagerRelationships(Pageable pageable) {
        return adventureAccountCharacterRepository.findAllWithEagerRelationships(pageable).map(adventureAccountCharacterMapper::toDto);
    }
    

    /**
     * Get one adventureAccountCharacter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AdventureAccountCharacterDTO> findOne(String id) {
        log.debug("Request to get AdventureAccountCharacter : {}", id);
        return adventureAccountCharacterRepository.findOneWithEagerRelationships(id)
            .map(adventureAccountCharacterMapper::toDto);
    }

    /**
     * Delete the adventureAccountCharacter by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete AdventureAccountCharacter : {}", id);
        adventureAccountCharacterRepository.deleteById(id);
        adventureAccountCharacterSearchRepository.deleteById(id);
    }

    /**
     * Search for the adventureAccountCharacter corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureAccountCharacterDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AdventureAccountCharacters for query {}", query);
        return adventureAccountCharacterSearchRepository.search(queryStringQuery(query), pageable)
            .map(adventureAccountCharacterMapper::toDto);
    }
}
