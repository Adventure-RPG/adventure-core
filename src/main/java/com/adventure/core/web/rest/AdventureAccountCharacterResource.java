package com.adventure.core.web.rest;

import com.adventure.core.service.AdventureAccountCharacterService;
import com.adventure.core.web.rest.errors.BadRequestAlertException;
import com.adventure.core.service.dto.AdventureAccountCharacterDTO;

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
 * REST controller for managing {@link com.adventure.core.domain.AdventureAccountCharacter}.
 */
@RestController
@RequestMapping("/api")
public class AdventureAccountCharacterResource {

    private final Logger log = LoggerFactory.getLogger(AdventureAccountCharacterResource.class);

    private static final String ENTITY_NAME = "adventureCoreAdventureAccountCharacter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdventureAccountCharacterService adventureAccountCharacterService;

    public AdventureAccountCharacterResource(AdventureAccountCharacterService adventureAccountCharacterService) {
        this.adventureAccountCharacterService = adventureAccountCharacterService;
    }

    /**
     * {@code POST  /adventure-account-characters} : Create a new adventureAccountCharacter.
     *
     * @param adventureAccountCharacterDTO the adventureAccountCharacterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adventureAccountCharacterDTO, or with status {@code 400 (Bad Request)} if the adventureAccountCharacter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adventure-account-characters")
    public ResponseEntity<AdventureAccountCharacterDTO> createAdventureAccountCharacter(@Valid @RequestBody AdventureAccountCharacterDTO adventureAccountCharacterDTO) throws URISyntaxException {
        log.debug("REST request to save AdventureAccountCharacter : {}", adventureAccountCharacterDTO);
        if (adventureAccountCharacterDTO.getId() != null) {
            throw new BadRequestAlertException("A new adventureAccountCharacter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdventureAccountCharacterDTO result = adventureAccountCharacterService.save(adventureAccountCharacterDTO);
        return ResponseEntity.created(new URI("/api/adventure-account-characters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adventure-account-characters} : Updates an existing adventureAccountCharacter.
     *
     * @param adventureAccountCharacterDTO the adventureAccountCharacterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adventureAccountCharacterDTO,
     * or with status {@code 400 (Bad Request)} if the adventureAccountCharacterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adventureAccountCharacterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adventure-account-characters")
    public ResponseEntity<AdventureAccountCharacterDTO> updateAdventureAccountCharacter(@Valid @RequestBody AdventureAccountCharacterDTO adventureAccountCharacterDTO) throws URISyntaxException {
        log.debug("REST request to update AdventureAccountCharacter : {}", adventureAccountCharacterDTO);
        if (adventureAccountCharacterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdventureAccountCharacterDTO result = adventureAccountCharacterService.save(adventureAccountCharacterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adventureAccountCharacterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adventure-account-characters} : get all the adventureAccountCharacters.
     *

     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adventureAccountCharacters in body.
     */
    @GetMapping("/adventure-account-characters")
    public ResponseEntity<List<AdventureAccountCharacterDTO>> getAllAdventureAccountCharacters(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of AdventureAccountCharacters");
        Page<AdventureAccountCharacterDTO> page;
        if (eagerload) {
            page = adventureAccountCharacterService.findAllWithEagerRelationships(pageable);
        } else {
            page = adventureAccountCharacterService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adventure-account-characters/:id} : get the "id" adventureAccountCharacter.
     *
     * @param id the id of the adventureAccountCharacterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adventureAccountCharacterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adventure-account-characters/{id}")
    public ResponseEntity<AdventureAccountCharacterDTO> getAdventureAccountCharacter(@PathVariable String id) {
        log.debug("REST request to get AdventureAccountCharacter : {}", id);
        Optional<AdventureAccountCharacterDTO> adventureAccountCharacterDTO = adventureAccountCharacterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adventureAccountCharacterDTO);
    }

    /**
     * {@code DELETE  /adventure-account-characters/:id} : delete the "id" adventureAccountCharacter.
     *
     * @param id the id of the adventureAccountCharacterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adventure-account-characters/{id}")
    public ResponseEntity<Void> deleteAdventureAccountCharacter(@PathVariable String id) {
        log.debug("REST request to delete AdventureAccountCharacter : {}", id);
        adventureAccountCharacterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/adventure-account-characters?query=:query} : search for the adventureAccountCharacter corresponding
     * to the query.
     *
     * @param query the query of the adventureAccountCharacter search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/adventure-account-characters")
    public ResponseEntity<List<AdventureAccountCharacterDTO>> searchAdventureAccountCharacters(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AdventureAccountCharacters for query {}", query);
        Page<AdventureAccountCharacterDTO> page = adventureAccountCharacterService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
