package com.adventure.core.service.impl;

import com.adventure.core.service.AdventureModelOptionsService;
import com.adventure.core.domain.AdventureModelOptions;
import com.adventure.core.repository.AdventureModelOptionsRepository;
import com.adventure.core.repository.search.AdventureModelOptionsSearchRepository;
import com.adventure.core.service.dto.AdventureModelOptionsDTO;
import com.adventure.core.service.mapper.AdventureModelOptionsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link AdventureModelOptions}.
 */
@Service
public class AdventureModelOptionsServiceImpl implements AdventureModelOptionsService {

    private final Logger log = LoggerFactory.getLogger(AdventureModelOptionsServiceImpl.class);

    private final AdventureModelOptionsRepository adventureModelOptionsRepository;

    private final AdventureModelOptionsMapper adventureModelOptionsMapper;

    private final AdventureModelOptionsSearchRepository adventureModelOptionsSearchRepository;

    public AdventureModelOptionsServiceImpl(AdventureModelOptionsRepository adventureModelOptionsRepository, AdventureModelOptionsMapper adventureModelOptionsMapper, AdventureModelOptionsSearchRepository adventureModelOptionsSearchRepository) {
        this.adventureModelOptionsRepository = adventureModelOptionsRepository;
        this.adventureModelOptionsMapper = adventureModelOptionsMapper;
        this.adventureModelOptionsSearchRepository = adventureModelOptionsSearchRepository;
    }

    /**
     * Save a adventureModelOptions.
     *
     * @param adventureModelOptionsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdventureModelOptionsDTO save(AdventureModelOptionsDTO adventureModelOptionsDTO) {
        log.debug("Request to save AdventureModelOptions : {}", adventureModelOptionsDTO);
        AdventureModelOptions adventureModelOptions = adventureModelOptionsMapper.toEntity(adventureModelOptionsDTO);
        adventureModelOptions = adventureModelOptionsRepository.save(adventureModelOptions);
        AdventureModelOptionsDTO result = adventureModelOptionsMapper.toDto(adventureModelOptions);
        adventureModelOptionsSearchRepository.save(adventureModelOptions);
        return result;
    }

    /**
     * Get all the adventureModelOptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureModelOptionsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdventureModelOptions");
        return adventureModelOptionsRepository.findAll(pageable)
            .map(adventureModelOptionsMapper::toDto);
    }


    /**
     * Get one adventureModelOptions by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AdventureModelOptionsDTO> findOne(String id) {
        log.debug("Request to get AdventureModelOptions : {}", id);
        return adventureModelOptionsRepository.findById(id)
            .map(adventureModelOptionsMapper::toDto);
    }

    /**
     * Delete the adventureModelOptions by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete AdventureModelOptions : {}", id);
        adventureModelOptionsRepository.deleteById(id);
        adventureModelOptionsSearchRepository.deleteById(id);
    }

    /**
     * Search for the adventureModelOptions corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureModelOptionsDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AdventureModelOptions for query {}", query);
        return adventureModelOptionsSearchRepository.search(queryStringQuery(query), pageable)
            .map(adventureModelOptionsMapper::toDto);
    }
}
