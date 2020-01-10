package com.adventure.core.service.impl;

import com.adventure.core.service.AdventureCategoryTypeService;
import com.adventure.core.domain.AdventureCategoryType;
import com.adventure.core.repository.AdventureCategoryTypeRepository;
import com.adventure.core.repository.search.AdventureCategoryTypeSearchRepository;
import com.adventure.core.service.dto.AdventureCategoryTypeDTO;
import com.adventure.core.service.mapper.AdventureCategoryTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link AdventureCategoryType}.
 */
@Service
public class AdventureCategoryTypeServiceImpl implements AdventureCategoryTypeService {

    private final Logger log = LoggerFactory.getLogger(AdventureCategoryTypeServiceImpl.class);

    private final AdventureCategoryTypeRepository adventureCategoryTypeRepository;

    private final AdventureCategoryTypeMapper adventureCategoryTypeMapper;

    private final AdventureCategoryTypeSearchRepository adventureCategoryTypeSearchRepository;

    public AdventureCategoryTypeServiceImpl(AdventureCategoryTypeRepository adventureCategoryTypeRepository, AdventureCategoryTypeMapper adventureCategoryTypeMapper, AdventureCategoryTypeSearchRepository adventureCategoryTypeSearchRepository) {
        this.adventureCategoryTypeRepository = adventureCategoryTypeRepository;
        this.adventureCategoryTypeMapper = adventureCategoryTypeMapper;
        this.adventureCategoryTypeSearchRepository = adventureCategoryTypeSearchRepository;
    }

    /**
     * Save a adventureCategoryType.
     *
     * @param adventureCategoryTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdventureCategoryTypeDTO save(AdventureCategoryTypeDTO adventureCategoryTypeDTO) {
        log.debug("Request to save AdventureCategoryType : {}", adventureCategoryTypeDTO);
        AdventureCategoryType adventureCategoryType = adventureCategoryTypeMapper.toEntity(adventureCategoryTypeDTO);
        adventureCategoryType = adventureCategoryTypeRepository.save(adventureCategoryType);
        AdventureCategoryTypeDTO result = adventureCategoryTypeMapper.toDto(adventureCategoryType);
        adventureCategoryTypeSearchRepository.save(adventureCategoryType);
        return result;
    }

    /**
     * Get all the adventureCategoryTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureCategoryTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdventureCategoryTypes");
        return adventureCategoryTypeRepository.findAll(pageable)
            .map(adventureCategoryTypeMapper::toDto);
    }


    /**
     * Get one adventureCategoryType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<AdventureCategoryTypeDTO> findOne(String id) {
        log.debug("Request to get AdventureCategoryType : {}", id);
        return adventureCategoryTypeRepository.findById(id)
            .map(adventureCategoryTypeMapper::toDto);
    }

    /**
     * Delete the adventureCategoryType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete AdventureCategoryType : {}", id);
        adventureCategoryTypeRepository.deleteById(id);
        adventureCategoryTypeSearchRepository.deleteById(id);
    }

    /**
     * Search for the adventureCategoryType corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<AdventureCategoryTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AdventureCategoryTypes for query {}", query);
        return adventureCategoryTypeSearchRepository.search(queryStringQuery(query), pageable)
            .map(adventureCategoryTypeMapper::toDto);
    }
}
