package com.adventure.core.web.rest;

import com.adventure.core.service.AdventureModelService;
import com.adventure.core.web.rest.errors.BadRequestAlertException;
import com.adventure.core.service.dto.AdventureModelDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.adventure.core.domain.AdventureModel}.
 */
@RestController
@RequestMapping("/api")
public class AdventureModelResource {

    private final Logger log = LoggerFactory.getLogger(AdventureModelResource.class);

    private static final String ENTITY_NAME = "adventureCoreAdventureModel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdventureModelService adventureModelService;

    public AdventureModelResource(AdventureModelService adventureModelService) {
        this.adventureModelService = adventureModelService;
    }

    /**
     * {@code POST  /adventure-models} : Create a new adventureModel.
     *
     * @param adventureModelDTO the adventureModelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adventureModelDTO, or with status {@code 400 (Bad Request)} if the adventureModel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adventure-models")
    public ResponseEntity<AdventureModelDTO> createAdventureModel(@RequestBody AdventureModelDTO adventureModelDTO) throws URISyntaxException {
        log.debug("REST request to save AdventureModel : {}", adventureModelDTO);
        if (adventureModelDTO.getId() != null) {
            throw new BadRequestAlertException("A new adventureModel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdventureModelDTO result = adventureModelService.save(adventureModelDTO);
        return ResponseEntity.created(new URI("/api/adventure-models/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adventure-models} : Updates an existing adventureModel.
     *
     * @param adventureModelDTO the adventureModelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adventureModelDTO,
     * or with status {@code 400 (Bad Request)} if the adventureModelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adventureModelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adventure-models")
    public ResponseEntity<AdventureModelDTO> updateAdventureModel(@RequestBody AdventureModelDTO adventureModelDTO) throws URISyntaxException {
        log.debug("REST request to update AdventureModel : {}", adventureModelDTO);
        if (adventureModelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdventureModelDTO result = adventureModelService.save(adventureModelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adventureModelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adventure-models} : get all the adventureModels.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adventureModels in body.
     */
    @GetMapping("/adventure-models")
    public ResponseEntity<List<AdventureModelDTO>> getAllAdventureModels(Pageable pageable) {
        log.debug("REST request to get a page of AdventureModels");
        Page<AdventureModelDTO> page = adventureModelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adventure-models/:id} : get the "id" adventureModel.
     *
     * @param id the id of the adventureModelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adventureModelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adventure-models/{id}")
    public ResponseEntity<AdventureModelDTO> getAdventureModel(@PathVariable String id) {
        log.debug("REST request to get AdventureModel : {}", id);
        Optional<AdventureModelDTO> adventureModelDTO = adventureModelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adventureModelDTO);
    }

    /**
     * {@code DELETE  /adventure-models/:id} : delete the "id" adventureModel.
     *
     * @param id the id of the adventureModelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adventure-models/{id}")
    public ResponseEntity<Void> deleteAdventureModel(@PathVariable String id) {
        log.debug("REST request to delete AdventureModel : {}", id);
        adventureModelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/adventure-models?query=:query} : search for the adventureModel corresponding
     * to the query.
     *
     * @param query the query of the adventureModel search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/adventure-models")
    public ResponseEntity<List<AdventureModelDTO>> searchAdventureModels(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AdventureModels for query {}", query);
        Page<AdventureModelDTO> page = adventureModelService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
