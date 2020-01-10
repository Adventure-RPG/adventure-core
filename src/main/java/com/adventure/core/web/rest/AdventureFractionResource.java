package com.adventure.core.web.rest;

import com.adventure.core.service.AdventureFractionService;
import com.adventure.core.web.rest.errors.BadRequestAlertException;
import com.adventure.core.service.dto.AdventureFractionDTO;

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
 * REST controller for managing {@link com.adventure.core.domain.AdventureFraction}.
 */
@RestController
@RequestMapping("/api")
public class AdventureFractionResource {

    private final Logger log = LoggerFactory.getLogger(AdventureFractionResource.class);

    private static final String ENTITY_NAME = "adventureCoreAdventureFraction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdventureFractionService adventureFractionService;

    public AdventureFractionResource(AdventureFractionService adventureFractionService) {
        this.adventureFractionService = adventureFractionService;
    }

    /**
     * {@code POST  /adventure-fractions} : Create a new adventureFraction.
     *
     * @param adventureFractionDTO the adventureFractionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adventureFractionDTO, or with status {@code 400 (Bad Request)} if the adventureFraction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adventure-fractions")
    public ResponseEntity<AdventureFractionDTO> createAdventureFraction(@Valid @RequestBody AdventureFractionDTO adventureFractionDTO) throws URISyntaxException {
        log.debug("REST request to save AdventureFraction : {}", adventureFractionDTO);
        if (adventureFractionDTO.getId() != null) {
            throw new BadRequestAlertException("A new adventureFraction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdventureFractionDTO result = adventureFractionService.save(adventureFractionDTO);
        return ResponseEntity.created(new URI("/api/adventure-fractions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adventure-fractions} : Updates an existing adventureFraction.
     *
     * @param adventureFractionDTO the adventureFractionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adventureFractionDTO,
     * or with status {@code 400 (Bad Request)} if the adventureFractionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adventureFractionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adventure-fractions")
    public ResponseEntity<AdventureFractionDTO> updateAdventureFraction(@Valid @RequestBody AdventureFractionDTO adventureFractionDTO) throws URISyntaxException {
        log.debug("REST request to update AdventureFraction : {}", adventureFractionDTO);
        if (adventureFractionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdventureFractionDTO result = adventureFractionService.save(adventureFractionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adventureFractionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adventure-fractions} : get all the adventureFractions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adventureFractions in body.
     */
    @GetMapping("/adventure-fractions")
    public ResponseEntity<List<AdventureFractionDTO>> getAllAdventureFractions(Pageable pageable) {
        log.debug("REST request to get a page of AdventureFractions");
        Page<AdventureFractionDTO> page = adventureFractionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adventure-fractions/:id} : get the "id" adventureFraction.
     *
     * @param id the id of the adventureFractionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adventureFractionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adventure-fractions/{id}")
    public ResponseEntity<AdventureFractionDTO> getAdventureFraction(@PathVariable String id) {
        log.debug("REST request to get AdventureFraction : {}", id);
        Optional<AdventureFractionDTO> adventureFractionDTO = adventureFractionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adventureFractionDTO);
    }

    /**
     * {@code DELETE  /adventure-fractions/:id} : delete the "id" adventureFraction.
     *
     * @param id the id of the adventureFractionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adventure-fractions/{id}")
    public ResponseEntity<Void> deleteAdventureFraction(@PathVariable String id) {
        log.debug("REST request to delete AdventureFraction : {}", id);
        adventureFractionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/adventure-fractions?query=:query} : search for the adventureFraction corresponding
     * to the query.
     *
     * @param query the query of the adventureFraction search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/adventure-fractions")
    public ResponseEntity<List<AdventureFractionDTO>> searchAdventureFractions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AdventureFractions for query {}", query);
        Page<AdventureFractionDTO> page = adventureFractionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
