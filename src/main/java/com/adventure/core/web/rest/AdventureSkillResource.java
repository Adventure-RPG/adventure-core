package com.adventure.core.web.rest;

import com.adventure.core.service.AdventureSkillService;
import com.adventure.core.web.rest.errors.BadRequestAlertException;
import com.adventure.core.service.dto.AdventureSkillDTO;

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
 * REST controller for managing {@link com.adventure.core.domain.AdventureSkill}.
 */
@RestController
@RequestMapping("/api")
public class AdventureSkillResource {

    private final Logger log = LoggerFactory.getLogger(AdventureSkillResource.class);

    private static final String ENTITY_NAME = "adventureCoreAdventureSkill";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdventureSkillService adventureSkillService;

    public AdventureSkillResource(AdventureSkillService adventureSkillService) {
        this.adventureSkillService = adventureSkillService;
    }

    /**
     * {@code POST  /adventure-skills} : Create a new adventureSkill.
     *
     * @param adventureSkillDTO the adventureSkillDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adventureSkillDTO, or with status {@code 400 (Bad Request)} if the adventureSkill has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adventure-skills")
    public ResponseEntity<AdventureSkillDTO> createAdventureSkill(@Valid @RequestBody AdventureSkillDTO adventureSkillDTO) throws URISyntaxException {
        log.debug("REST request to save AdventureSkill : {}", adventureSkillDTO);
        if (adventureSkillDTO.getId() != null) {
            throw new BadRequestAlertException("A new adventureSkill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdventureSkillDTO result = adventureSkillService.save(adventureSkillDTO);
        return ResponseEntity.created(new URI("/api/adventure-skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adventure-skills} : Updates an existing adventureSkill.
     *
     * @param adventureSkillDTO the adventureSkillDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adventureSkillDTO,
     * or with status {@code 400 (Bad Request)} if the adventureSkillDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adventureSkillDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adventure-skills")
    public ResponseEntity<AdventureSkillDTO> updateAdventureSkill(@Valid @RequestBody AdventureSkillDTO adventureSkillDTO) throws URISyntaxException {
        log.debug("REST request to update AdventureSkill : {}", adventureSkillDTO);
        if (adventureSkillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdventureSkillDTO result = adventureSkillService.save(adventureSkillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adventureSkillDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adventure-skills} : get all the adventureSkills.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adventureSkills in body.
     */
    @GetMapping("/adventure-skills")
    public ResponseEntity<List<AdventureSkillDTO>> getAllAdventureSkills(Pageable pageable) {
        log.debug("REST request to get a page of AdventureSkills");
        Page<AdventureSkillDTO> page = adventureSkillService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /adventure-skills/:id} : get the "id" adventureSkill.
     *
     * @param id the id of the adventureSkillDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adventureSkillDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adventure-skills/{id}")
    public ResponseEntity<AdventureSkillDTO> getAdventureSkill(@PathVariable String id) {
        log.debug("REST request to get AdventureSkill : {}", id);
        Optional<AdventureSkillDTO> adventureSkillDTO = adventureSkillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adventureSkillDTO);
    }

    /**
     * {@code DELETE  /adventure-skills/:id} : delete the "id" adventureSkill.
     *
     * @param id the id of the adventureSkillDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adventure-skills/{id}")
    public ResponseEntity<Void> deleteAdventureSkill(@PathVariable String id) {
        log.debug("REST request to delete AdventureSkill : {}", id);
        adventureSkillService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/adventure-skills?query=:query} : search for the adventureSkill corresponding
     * to the query.
     *
     * @param query the query of the adventureSkill search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/adventure-skills")
    public ResponseEntity<List<AdventureSkillDTO>> searchAdventureSkills(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AdventureSkills for query {}", query);
        Page<AdventureSkillDTO> page = adventureSkillService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
