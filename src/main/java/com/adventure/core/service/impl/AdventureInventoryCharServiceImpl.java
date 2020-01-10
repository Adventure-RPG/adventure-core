package com.adventure.core.service.impl;

import com.adventure.core.service.AdventureInventoryCharService;
import com.adventure.core.domain.AdventureInventoryChar;
import com.adventure.core.repository.AdventureInventoryCharRepository;
import com.adventure.core.repository.search.AdventureInventoryCharSearchRepository;
import com.adventure.core.service.dto.AdventureInventoryCharDTO;
import com.adventure.core.service.mapper.AdventureInventoryCharMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link AdventureInventoryChar}.
 */
@Service
public class AdventureInventoryCharServiceImpl implements AdventureInventoryCharService {

    private final Logger log = LoggerFactory.getLogger(AdventureInventoryCharServiceImpl.class);

    private final AdventureInventoryCharRepository adventureInventoryCharRepository;

    private final AdventureInventoryCharMapper adventureInventoryCharMapper;

    private final AdventureInventoryCharSearchRepository adventureInventoryCharSearchRepository;

    public AdventureInventoryCharServiceImpl(AdventureInventoryCharRepository adventureInventoryCharRepository, AdventureInventoryCharMapper adventureInventoryCharMapper, AdventureInventoryCharSearchRepository adventureInventoryCharSearchRepository) {
        this.adventureInventoryCharRepository = adventureInventoryCharRepository;
        this.adventureInventoryCharMapper = adventureInventoryCharMapper;
        this.adventureInventoryCharSearchRepository = adventureInventoryCharSearchRepository;
    }

    /**
     * Save a adventureInventoryChar.
     *
     * @param adventureInventoryCharDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdventureInventoryCharDTO save(AdventureInventoryCharDTO adventureInventoryCharDTO) {
        log.debug("Request to save AdventureInventoryChar : {}", adventureInventoryCharDTO);
        AdventureInventoryChar adventureInventoryChar = adventureInventoryCharMapper.toEntity(adventureInventoryCharDTO);
        adventureInventoryChar = adventureInventoryCharRepository.save(adventureInventoryChar);
        AdventureInventoryCharDTO result = adventureInventoryCharMapper.toDto(adventureInventoryChar);
        adventureInventoryCharSearchRepository.save(adventureInventoryChar);
        return result;
    }

    /**
     * Get all the adventureInventoryChars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureInventoryCharDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdventureInventoryChars");
        return adventureInventoryCharRepository.findAll(pageable)
            .map(adventureInventoryCharMapper::toDto);
    }


    /**
     * Get one adventureInventoryChar by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AdventureInventoryCharDTO> findOne(String id) {
        log.debug("Request to get AdventureInventoryChar : {}", id);
        return adventureInventoryCharRepository.findById(id)
            .map(adventureInventoryCharMapper::toDto);
    }

    /**
     * Delete the adventureInventoryChar by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete AdventureInventoryChar : {}", id);
        adventureInventoryCharRepository.deleteById(id);
        adventureInventoryCharSearchRepository.deleteById(id);
    }

    /**
     * Search for the adventureInventoryChar corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureInventoryCharDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AdventureInventoryChars for query {}", query);
        return adventureInventoryCharSearchRepository.search(queryStringQuery(query), pageable)
            .map(adventureInventoryCharMapper::toDto);
    }
}
