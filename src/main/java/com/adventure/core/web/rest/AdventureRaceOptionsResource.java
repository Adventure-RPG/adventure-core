package com.adventure.core.web.rest;

import com.adventure.core.service.AdventureRaceOptionsService;
import com.adventure.core.web.rest.errors.BadRequestAlertException;
import com.adventure.core.service.dto.AdventureRaceOptionsDTO;

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
 * REST controller for managing {@link com.adventure.core.domain.AdventureRaceOptions}.
 */
@RestController
@RequestMapping("/api")
public class AdventureRaceOptionsResource {

    private final Logger log = LoggerFactory.getLogger(AdventureRaceOptionsResource.class);

    private static final String ENTITY_NAME = "adventureCoreAdventureRaceOptions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdventureRaceOptionsService adventureRaceOptionsService;

    public AdventureRaceOptionsResource(AdventureRaceOptionsService adventureRaceOptionsService) {
        this.adventureRaceOptionsService = adventureRaceOptionsService;
    }

    /**
     * {@code POST  /adventure-race-options} : Create a new adventureRaceOptions.
     *
     * @param adventureRaceOptionsDTO the adventureRaceOptionsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adventureRaceOptionsDTO, or with status {@code 400 (Bad Request)} if the adventureRaceOptions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adventure-race-options")
    public ResponseEntity<AdventureRaceOptionsDTO> createAdventureRaceOptions(@Valid @RequestBody AdventureRaceOptionsDTO adventureRaceOptionsDTO) throws URISyntaxException {
        log.debug("REST request to save AdventureRaceOptions : {}", adventureRaceOptionsDTO);
        if (adventureRaceOptionsDTO.getId() != null) {
            throw new BadRequestAlertException("A new adventureRaceOptions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdventureRaceOptionsDTO result = adventureRaceOptionsService.save(adventureRaceOptionsDTO);
        return ResponseEntity.created(new URI("/api/adventure-race-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adventure-race-options} : Updates an existing adventureRaceOptions.
     *
     * @param adventureRaceOptionsDTO the adventureRaceOptionsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adventureRaceOptionsDTO,
     * or with status {@code 400 (Bad Request)} if the adventureRaceOptionsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adventureRaceOptionsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adventure-race-options")
    public ResponseEntity<AdventureRaceOptionsDTO> updateAdventureRaceOptions(@Valid @RequestBody AdventureRaceOptionsDTO adventureRaceOptionsDTO) throws URISyntaxException {
        log.debug("REST request to update AdventureRaceOptions : {}", adventureRaceOptionsDTO);
        if (adventureRaceOptionsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdventureRaceOptionsDTO result = adventureRaceOptionsService.save(adventureRaceOptionsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adventureRaceOptionsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adventure-race-options} : get all the adventureRaceOptions.
     *

     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adventureRaceOptions in body.
     */
    @GetMapping("/adventure-race-options")
    public ResponseEntity<List<AdventureRaceOptionsDTO>> getAllAdventureRaceOptions(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of AdventureRaceOptions");
        Page<AdventureRaceOptionsDTO> page;
        if (eagerload) {
            page = adventureRaceOptionsService.findAllWithEagerRelationships(pageable);
        } else {
            page = adventureRaceOptionsService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adventure-race-options/:id} : get the "id" adventureRaceOptions.
     *
     * @param id the id of the adventureRaceOptionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adventureRaceOptionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adventure-race-options/{id}")
    public ResponseEntity<AdventureRaceOptionsDTO> getAdventureRaceOptions(@PathVariable String id) {
        log.debug("REST request to get AdventureRaceOptions : {}", id);
        Optional<AdventureRaceOptionsDTO> adventureRaceOptionsDTO = adventureRaceOptionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adventureRaceOptionsDTO);
    }

    /**
     * {@code DELETE  /adventure-race-options/:id} : delete the "id" adventureRaceOptions.
     *
     * @param id the id of the adventureRaceOptionsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adventure-race-options/{id}")
    public ResponseEntity<Void> deleteAdventureRaceOptions(@PathVariable String id) {
        log.debug("REST request to delete AdventureRaceOptions : {}", id);
        adventureRaceOptionsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/adventure-race-options?query=:query} : search for the adventureRaceOptions corresponding
     * to the query.
     *
     * @param query the query of the adventureRaceOptions search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/adventure-race-options")
    public ResponseEntity<List<AdventureRaceOptionsDTO>> searchAdventureRaceOptions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AdventureRaceOptions for query {}", query);
        Page<AdventureRaceOptionsDTO> page = adventureRaceOptionsService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
