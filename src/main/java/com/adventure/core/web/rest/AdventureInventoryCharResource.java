package com.adventure.core.web.rest;

import com.adventure.core.service.AdventureInventoryCharService;
import com.adventure.core.web.rest.errors.BadRequestAlertException;
import com.adventure.core.service.dto.AdventureInventoryCharDTO;

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
 * REST controller for managing {@link com.adventure.core.domain.AdventureInventoryChar}.
 */
@RestController
@RequestMapping("/api")
public class AdventureInventoryCharResource {

    private final Logger log = LoggerFactory.getLogger(AdventureInventoryCharResource.class);

    private static final String ENTITY_NAME = "adventureCoreAdventureInventoryChar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdventureInventoryCharService adventureInventoryCharService;

    public AdventureInventoryCharResource(AdventureInventoryCharService adventureInventoryCharService) {
        this.adventureInventoryCharService = adventureInventoryCharService;
    }

    /**
     * {@code POST  /adventure-inventory-chars} : Create a new adventureInventoryChar.
     *
     * @param adventureInventoryCharDTO the adventureInventoryCharDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adventureInventoryCharDTO, or with status {@code 400 (Bad Request)} if the adventureInventoryChar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adventure-inventory-chars")
    public ResponseEntity<AdventureInventoryCharDTO> createAdventureInventoryChar(@RequestBody AdventureInventoryCharDTO adventureInventoryCharDTO) throws URISyntaxException {
        log.debug("REST request to save AdventureInventoryChar : {}", adventureInventoryCharDTO);
        if (adventureInventoryCharDTO.getId() != null) {
            throw new BadRequestAlertException("A new adventureInventoryChar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdventureInventoryCharDTO result = adventureInventoryCharService.save(adventureInventoryCharDTO);
        return ResponseEntity.created(new URI("/api/adventure-inventory-chars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adventure-inventory-chars} : Updates an existing adventureInventoryChar.
     *
     * @param adventureInventoryCharDTO the adventureInventoryCharDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adventureInventoryCharDTO,
     * or with status {@code 400 (Bad Request)} if the adventureInventoryCharDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adventureInventoryCharDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adventure-inventory-chars")
    public ResponseEntity<AdventureInventoryCharDTO> updateAdventureInventoryChar(@RequestBody AdventureInventoryCharDTO adventureInventoryCharDTO) throws URISyntaxException {
        log.debug("REST request to update AdventureInventoryChar : {}", adventureInventoryCharDTO);
        if (adventureInventoryCharDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdventureInventoryCharDTO result = adventureInventoryCharService.save(adventureInventoryCharDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adventureInventoryCharDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adventure-inventory-chars} : get all the adventureInventoryChars.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adventureInventoryChars in body.
     */
    @GetMapping("/adventure-inventory-chars")
    public ResponseEntity<List<AdventureInventoryCharDTO>> getAllAdventureInventoryChars(Pageable pageable) {
        log.debug("REST request to get a page of AdventureInventoryChars");
        Page<AdventureInventoryCharDTO> page = adventureInventoryCharService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adventure-inventory-chars/:id} : get the "id" adventureInventoryChar.
     *
     * @param id the id of the adventureInventoryCharDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adventureInventoryCharDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adventure-inventory-chars/{id}")
    public ResponseEntity<AdventureInventoryCharDTO> getAdventureInventoryChar(@PathVariable String id) {
        log.debug("REST request to get AdventureInventoryChar : {}", id);
        Optional<AdventureInventoryCharDTO> adventureInventoryCharDTO = adventureInventoryCharService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adventureInventoryCharDTO);
    }

    /**
     * {@code DELETE  /adventure-inventory-chars/:id} : delete the "id" adventureInventoryChar.
     *
     * @param id the id of the adventureInventoryCharDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adventure-inventory-chars/{id}")
    public ResponseEntity<Void> deleteAdventureInventoryChar(@PathVariable String id) {
        log.debug("REST request to delete AdventureInventoryChar : {}", id);
        adventureInventoryCharService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/adventure-inventory-chars?query=:query} : search for the adventureInventoryChar corresponding
     * to the query.
     *
     * @param query the query of the adventureInventoryChar search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/adventure-inventory-chars")
    public ResponseEntity<List<AdventureInventoryCharDTO>> searchAdventureInventoryChars(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AdventureInventoryChars for query {}", query);
        Page<AdventureInventoryCharDTO> page = adventureInventoryCharService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
