package com.adventure.core.web.rest;

import com.adventure.core.service.AdventureAttributesService;
import com.adventure.core.web.rest.errors.BadRequestAlertException;
import com.adventure.core.service.dto.AdventureAttributesDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.adventure.core.domain.AdventureAttributes}.
 */
@RestController
@RequestMapping("/api")
public class AdventureAttributesResource {

    private final Logger log = LoggerFactory.getLogger(AdventureAttributesResource.class);

    private static final String ENTITY_NAME = "adventureCoreAdventureAttributes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdventureAttributesService adventureAttributesService;

    public AdventureAttributesResource(AdventureAttributesService adventureAttributesService) {
        this.adventureAttributesService = adventureAttributesService;
    }

    /**
     * {@code POST  /adventure-attributes} : Create a new adventureAttributes.
     *
     * @param adventureAttributesDTO the adventureAttributesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adventureAttributesDTO, or with status {@code 400 (Bad Request)} if the adventureAttributes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adventure-attributes")
    public ResponseEntity<AdventureAttributesDTO> createAdventureAttributes(@Valid @RequestBody AdventureAttributesDTO adventureAttributesDTO) throws URISyntaxException {
        log.debug("REST request to save AdventureAttributes : {}", adventureAttributesDTO);
        if (adventureAttributesDTO.getId() != null) {
            throw new BadRequestAlertException("A new adventureAttributes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdventureAttributesDTO result = adventureAttributesService.save(adventureAttributesDTO);
        return ResponseEntity.created(new URI("/api/adventure-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adventure-attributes} : Updates an existing adventureAttributes.
     *
     * @param adventureAttributesDTO the adventureAttributesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adventureAttributesDTO,
     * or with status {@code 400 (Bad Request)} if the adventureAttributesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adventureAttributesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adventure-attributes")
    public ResponseEntity<AdventureAttributesDTO> updateAdventureAttributes(@Valid @RequestBody AdventureAttributesDTO adventureAttributesDTO) throws URISyntaxException {
        log.debug("REST request to update AdventureAttributes : {}", adventureAttributesDTO);
        if (adventureAttributesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdventureAttributesDTO result = adventureAttributesService.save(adventureAttributesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adventureAttributesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adventure-attributes} : get all the adventureAttributes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adventureAttributes in body.
     */
    @GetMapping("/adventure-attributes")
    public ResponseEntity<List<AdventureAttributesDTO>> getAllAdventureAttributes(Pageable pageable) {
        log.debug("REST request to get a page of AdventureAttributes");
        Page<AdventureAttributesDTO> page = adventureAttributesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adventure-attributes/:id} : get the "id" adventureAttributes.
     *
     * @param id the id of the adventureAttributesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adventureAttributesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adventure-attributes/{id}")
    public ResponseEntity<AdventureAttributesDTO> getAdventureAttributes(@PathVariable String id) {
        log.debug("REST request to get AdventureAttributes : {}", id);
        Optional<AdventureAttributesDTO> adventureAttributesDTO = adventureAttributesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adventureAttributesDTO);
    }

    /**
     * {@code DELETE  /adventure-attributes/:id} : delete the "id" adventureAttributes.
     *
     * @param id the id of the adventureAttributesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adventure-attributes/{id}")
    public ResponseEntity<Void> deleteAdventureAttributes(@PathVariable String id) {
        log.debug("REST request to delete AdventureAttributes : {}", id);
        adventureAttributesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/adventure-attributes?query=:query} : search for the adventureAttributes corresponding
     * to the query.
     *
     * @param query the query of the adventureAttributes search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/adventure-attributes")
    public ResponseEntity<List<AdventureAttributesDTO>> searchAdventureAttributes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AdventureAttributes for query {}", query);
        Page<AdventureAttributesDTO> page = adventureAttributesService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
