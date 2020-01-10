package com.adventure.core.web.rest;

import com.adventure.core.service.AdventureCharacteristicService;
import com.adventure.core.web.rest.errors.BadRequestAlertException;
import com.adventure.core.service.dto.AdventureCharacteristicDTO;

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
 * REST controller for managing {@link com.adventure.core.domain.AdventureCharacteristic}.
 */
@RestController
@RequestMapping("/api")
public class AdventureCharacteristicResource {

    private final Logger log = LoggerFactory.getLogger(AdventureCharacteristicResource.class);

    private static final String ENTITY_NAME = "adventureCoreAdventureCharacteristic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdventureCharacteristicService adventureCharacteristicService;

    public AdventureCharacteristicResource(AdventureCharacteristicService adventureCharacteristicService) {
        this.adventureCharacteristicService = adventureCharacteristicService;
    }

    /**
     * {@code POST  /adventure-characteristics} : Create a new adventureCharacteristic.
     *
     * @param adventureCharacteristicDTO the adventureCharacteristicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adventureCharacteristicDTO, or with status {@code 400 (Bad Request)} if the adventureCharacteristic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adventure-characteristics")
    public ResponseEntity<AdventureCharacteristicDTO> createAdventureCharacteristic(@Valid @RequestBody AdventureCharacteristicDTO adventureCharacteristicDTO) throws URISyntaxException {
        log.debug("REST request to save AdventureCharacteristic : {}", adventureCharacteristicDTO);
        if (adventureCharacteristicDTO.getId() != null) {
            throw new BadRequestAlertException("A new adventureCharacteristic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdventureCharacteristicDTO result = adventureCharacteristicService.save(adventureCharacteristicDTO);
        return ResponseEntity.created(new URI("/api/adventure-characteristics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adventure-characteristics} : Updates an existing adventureCharacteristic.
     *
     * @param adventureCharacteristicDTO the adventureCharacteristicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adventureCharacteristicDTO,
     * or with status {@code 400 (Bad Request)} if the adventureCharacteristicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adventureCharacteristicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adventure-characteristics")
    public ResponseEntity<AdventureCharacteristicDTO> updateAdventureCharacteristic(@Valid @RequestBody AdventureCharacteristicDTO adventureCharacteristicDTO) throws URISyntaxException {
        log.debug("REST request to update AdventureCharacteristic : {}", adventureCharacteristicDTO);
        if (adventureCharacteristicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdventureCharacteristicDTO result = adventureCharacteristicService.save(adventureCharacteristicDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adventureCharacteristicDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adventure-characteristics} : get all the adventureCharacteristics.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adventureCharacteristics in body.
     */
    @GetMapping("/adventure-characteristics")
    public ResponseEntity<List<AdventureCharacteristicDTO>> getAllAdventureCharacteristics(Pageable pageable) {
        log.debug("REST request to get a page of AdventureCharacteristics");
        Page<AdventureCharacteristicDTO> page = adventureCharacteristicService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adventure-characteristics/:id} : get the "id" adventureCharacteristic.
     *
     * @param id the id of the adventureCharacteristicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adventureCharacteristicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adventure-characteristics/{id}")
    public ResponseEntity<AdventureCharacteristicDTO> getAdventureCharacteristic(@PathVariable String id) {
        log.debug("REST request to get AdventureCharacteristic : {}", id);
        Optional<AdventureCharacteristicDTO> adventureCharacteristicDTO = adventureCharacteristicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adventureCharacteristicDTO);
    }

    /**
     * {@code DELETE  /adventure-characteristics/:id} : delete the "id" adventureCharacteristic.
     *
     * @param id the id of the adventureCharacteristicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adventure-characteristics/{id}")
    public ResponseEntity<Void> deleteAdventureCharacteristic(@PathVariable String id) {
        log.debug("REST request to delete AdventureCharacteristic : {}", id);
        adventureCharacteristicService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/adventure-characteristics?query=:query} : search for the adventureCharacteristic corresponding
     * to the query.
     *
     * @param query the query of the adventureCharacteristic search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/adventure-characteristics")
    public ResponseEntity<List<AdventureCharacteristicDTO>> searchAdventureCharacteristics(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AdventureCharacteristics for query {}", query);
        Page<AdventureCharacteristicDTO> page = adventureCharacteristicService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
