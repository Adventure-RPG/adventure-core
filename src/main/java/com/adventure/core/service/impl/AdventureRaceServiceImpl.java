package com.adventure.core.service.impl;

import com.adventure.core.service.AdventureRaceService;
import com.adventure.core.domain.AdventureRace;
import com.adventure.core.repository.AdventureRaceRepository;
import com.adventure.core.repository.search.AdventureRaceSearchRepository;
import com.adventure.core.service.dto.AdventureRaceDTO;
import com.adventure.core.service.mapper.AdventureRaceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link AdventureRace}.
 */
@Service
public class AdventureRaceServiceImpl implements AdventureRaceService {

    private final Logger log = LoggerFactory.getLogger(AdventureRaceServiceImpl.class);

    private final AdventureRaceRepository adventureRaceRepository;

    private final AdventureRaceMapper adventureRaceMapper;

    private final AdventureRaceSearchRepository adventureRaceSearchRepository;

    public AdventureRaceServiceImpl(AdventureRaceRepository adventureRaceRepository, AdventureRaceMapper adventureRaceMapper, AdventureRaceSearchRepository adventureRaceSearchRepository) {
        this.adventureRaceRepository = adventureRaceRepository;
        this.adventureRaceMapper = adventureRaceMapper;
        this.adventureRaceSearchRepository = adventureRaceSearchRepository;
    }

    /**
     * Save a adventureRace.
     *
     * @param adventureRaceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdventureRaceDTO save(AdventureRaceDTO adventureRaceDTO) {
        log.debug("Request to save AdventureRace : {}", adventureRaceDTO);
        AdventureRace adventureRace = adventureRaceMapper.toEntity(adventureRaceDTO);
        adventureRace = adventureRaceRepository.save(adventureRace);
        AdventureRaceDTO result = adventureRaceMapper.toDto(adventureRace);
        adventureRaceSearchRepository.save(adventureRace);
        return result;
    }

    /**
     * Get all the adventureRaces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureRaceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdventureRaces");
        return adventureRaceRepository.findAll(pageable)
            .map(adventureRaceMapper::toDto);
    }

    /**
     * Get all the adventureRaces with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<AdventureRaceDTO> findAllWithEagerRelationships(Pageable pageable) {
        return adventureRaceRepository.findAllWithEagerRelationships(pageable).map(adventureRaceMapper::toDto);
    }
    

    /**
     * Get one adventureRace by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AdventureRaceDTO> findOne(String id) {
        log.debug("Request to get AdventureRace : {}", id);
        return adventureRaceRepository.findOneWithEagerRelationships(id)
            .map(adventureRaceMapper::toDto);
    }

    /**
     * Delete the adventureRace by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete AdventureRace : {}", id);
        adventureRaceRepository.deleteById(id);
        adventureRaceSearchRepository.deleteById(id);
    }

    /**
     * Search for the adventureRace corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureRaceDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AdventureRaces for query {}", query);
        return adventureRaceSearchRepository.search(queryStringQuery(query), pageable)
            .map(adventureRaceMapper::toDto);
    }
}
