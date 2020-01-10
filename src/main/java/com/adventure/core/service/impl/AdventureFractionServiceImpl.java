package com.adventure.core.service.impl;

import com.adventure.core.service.AdventureFractionService;
import com.adventure.core.domain.AdventureFraction;
import com.adventure.core.repository.AdventureFractionRepository;
import com.adventure.core.repository.search.AdventureFractionSearchRepository;
import com.adventure.core.service.dto.AdventureFractionDTO;
import com.adventure.core.service.mapper.AdventureFractionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link AdventureFraction}.
 */
@Service
public class AdventureFractionServiceImpl implements AdventureFractionService {

    private final Logger log = LoggerFactory.getLogger(AdventureFractionServiceImpl.class);

    private final AdventureFractionRepository adventureFractionRepository;

    private final AdventureFractionMapper adventureFractionMapper;

    private final AdventureFractionSearchRepository adventureFractionSearchRepository;

    public AdventureFractionServiceImpl(AdventureFractionRepository adventureFractionRepository, AdventureFractionMapper adventureFractionMapper, AdventureFractionSearchRepository adventureFractionSearchRepository) {
        this.adventureFractionRepository = adventureFractionRepository;
        this.adventureFractionMapper = adventureFractionMapper;
        this.adventureFractionSearchRepository = adventureFractionSearchRepository;
    }

    /**
     * Save a adventureFraction.
     *
     * @param adventureFractionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdventureFractionDTO save(AdventureFractionDTO adventureFractionDTO) {
        log.debug("Request to save AdventureFraction : {}", adventureFractionDTO);
        AdventureFraction adventureFraction = adventureFractionMapper.toEntity(adventureFractionDTO);
        adventureFraction = adventureFractionRepository.save(adventureFraction);
        AdventureFractionDTO result = adventureFractionMapper.toDto(adventureFraction);
        adventureFractionSearchRepository.save(adventureFraction);
        return result;
    }

    /**
     * Get all the adventureFractions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureFractionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdventureFractions");
        return adventureFractionRepository.findAll(pageable)
            .map(adventureFractionMapper::toDto);
    }


    /**
     * Get one adventureFraction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AdventureFractionDTO> findOne(String id) {
        log.debug("Request to get AdventureFraction : {}", id);
        return adventureFractionRepository.findById(id)
            .map(adventureFractionMapper::toDto);
    }

    /**
     * Delete the adventureFraction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete AdventureFraction : {}", id);
        adventureFractionRepository.deleteById(id);
        adventureFractionSearchRepository.deleteById(id);
    }

    /**
     * Search for the adventureFraction corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureFractionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AdventureFractions for query {}", query);
        return adventureFractionSearchRepository.search(queryStringQuery(query), pageable)
            .map(adventureFractionMapper::toDto);
    }
}
