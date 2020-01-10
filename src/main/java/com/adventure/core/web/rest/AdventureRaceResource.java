package com.adventure.core.web.rest;

import com.adventure.core.service.AdventureRaceService;
import com.adventure.core.web.rest.errors.BadRequestAlertException;
import com.adventure.core.service.dto.AdventureRaceDTO;

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
 * REST controller for managing {@link com.adventure.core.domain.AdventureRace}.
 */
@RestController
@RequestMapping("/api")
public class AdventureRaceResource {

    private final Logger log = LoggerFactory.getLogger(AdventureRaceResource.class);

    private static final String ENTITY_NAME = "adventureCoreAdventureRace";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdventureRaceService adventureRaceService;

    public AdventureRaceResource(AdventureRaceService adventureRaceService) {
        this.adventureRaceService = adventureRaceService;
    }

    /**
     * {@code POST  /adventure-races} : Create a new adventureRace.
     *
     * @param adventureRaceDTO the adventureRaceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adventureRaceDTO, or with status {@code 400 (Bad Request)} if the adventureRace has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adventure-races")
    public ResponseEntity<AdventureRaceDTO> createAdventureRace(@Valid @RequestBody AdventureRaceDTO adventureRaceDTO) throws URISyntaxException {
        log.debug("REST request to save AdventureRace : {}", adventureRaceDTO);
        if (adventureRaceDTO.getId() != null) {
            throw new BadRequestAlertException("A new adventureRace cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdventureRaceDTO result = adventureRaceService.save(adventureRaceDTO);
        return ResponseEntity.created(new URI("/api/adventure-races/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adventure-races} : Updates an existing adventureRace.
     *
     * @param adventureRaceDTO the adventureRaceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adventureRaceDTO,
     * or with status {@code 400 (Bad Request)} if the adventureRaceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adventureRaceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adventure-races")
    public ResponseEntity<AdventureRaceDTO> updateAdventureRace(@Valid @RequestBody AdventureRaceDTO adventureRaceDTO) throws URISyntaxException {
        log.debug("REST request to update AdventureRace : {}", adventureRaceDTO);
        if (adventureRaceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdventureRaceDTO result = adventureRaceService.save(adventureRaceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adventureRaceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adventure-races} : get all the adventureRaces.
     *

     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adventureRaces in body.
     */
    @GetMapping("/adventure-races")
    public ResponseEntity<List<AdventureRaceDTO>> getAllAdventureRaces(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of AdventureRaces");
        Page<AdventureRaceDTO> page;
        if (eagerload) {
            page = adventureRaceService.findAllWithEagerRelationships(pageable);
        } else {
            page = adventureRaceService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adventure-races/:id} : get the "id" adventureRace.
     *
     * @param id the id of the adventureRaceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adventureRaceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adventure-races/{id}")
    public ResponseEntity<AdventureRaceDTO> getAdventureRace(@PathVariable String id) {
        log.debug("REST request to get AdventureRace : {}", id);
        Optional<AdventureRaceDTO> adventureRaceDTO = adventureRaceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adventureRaceDTO);
    }

    /**
     * {@code DELETE  /adventure-races/:id} : delete the "id" adventureRace.
     *
     * @param id the id of the adventureRaceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adventure-races/{id}")
    public ResponseEntity<Void> deleteAdventureRace(@PathVariable String id) {
        log.debug("REST request to delete AdventureRace : {}", id);
        adventureRaceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/adventure-races?query=:query} : search for the adventureRace corresponding
     * to the query.
     *
     * @param query the query of the adventureRace search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/adventure-races")
    public ResponseEntity<List<AdventureRaceDTO>> searchAdventureRaces(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AdventureRaces for query {}", query);
        Page<AdventureRaceDTO> page = adventureRaceService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
