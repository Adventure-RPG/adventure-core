package com.adventure.core.web.rest;

import com.adventure.core.AdventureCoreApp;
import com.adventure.core.config.SecurityBeanOverrideConfiguration;
import com.adventure.core.domain.AdventureInventoryChar;
import com.adventure.core.repository.AdventureInventoryCharRepository;
import com.adventure.core.repository.search.AdventureInventoryCharSearchRepository;
import com.adventure.core.service.AdventureInventoryCharService;
import com.adventure.core.service.dto.AdventureInventoryCharDTO;
import com.adventure.core.service.mapper.AdventureInventoryCharMapper;
import com.adventure.core.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.util.Collections;
import java.util.List;

import static com.adventure.core.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdventureInventoryCharResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, AdventureCoreApp.class})
public class AdventureInventoryCharResourceIT {

    @Autowired
    private AdventureInventoryCharRepository adventureInventoryCharRepository;

    @Autowired
    private AdventureInventoryCharMapper adventureInventoryCharMapper;

    @Autowired
    private AdventureInventoryCharService adventureInventoryCharService;

    /**
     * This repository is mocked in the com.adventure.core.repository.search test package.
     *
     * @see com.adventure.core.repository.search.AdventureInventoryCharSearchRepositoryMockConfiguration
     */
    @Autowired
    private AdventureInventoryCharSearchRepository mockAdventureInventoryCharSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAdventureInventoryCharMockMvc;

    private AdventureInventoryChar adventureInventoryChar;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdventureInventoryCharResource adventureInventoryCharResource = new AdventureInventoryCharResource(adventureInventoryCharService);
        this.restAdventureInventoryCharMockMvc = MockMvcBuilders.standaloneSetup(adventureInventoryCharResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdventureInventoryChar createEntity() {
        AdventureInventoryChar adventureInventoryChar = new AdventureInventoryChar();
        return adventureInventoryChar;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdventureInventoryChar createUpdatedEntity() {
        AdventureInventoryChar adventureInventoryChar = new AdventureInventoryChar();
        return adventureInventoryChar;
    }

    @BeforeEach
    public void initTest() {
        adventureInventoryCharRepository.deleteAll();
        adventureInventoryChar = createEntity();
    }

    @Test
    public void createAdventureInventoryChar() throws Exception {
        int databaseSizeBeforeCreate = adventureInventoryCharRepository.findAll().size();

        // Create the AdventureInventoryChar
        AdventureInventoryCharDTO adventureInventoryCharDTO = adventureInventoryCharMapper.toDto(adventureInventoryChar);
        restAdventureInventoryCharMockMvc.perform(post("/api/adventure-inventory-chars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureInventoryCharDTO)))
            .andExpect(status().isCreated());

        // Validate the AdventureInventoryChar in the database
        List<AdventureInventoryChar> adventureInventoryCharList = adventureInventoryCharRepository.findAll();
        assertThat(adventureInventoryCharList).hasSize(databaseSizeBeforeCreate + 1);
        AdventureInventoryChar testAdventureInventoryChar = adventureInventoryCharList.get(adventureInventoryCharList.size() - 1);

        // Validate the AdventureInventoryChar in Elasticsearch
        verify(mockAdventureInventoryCharSearchRepository, times(1)).save(testAdventureInventoryChar);
    }

    @Test
    public void createAdventureInventoryCharWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adventureInventoryCharRepository.findAll().size();

        // Create the AdventureInventoryChar with an existing ID
        adventureInventoryChar.setId("existing_id");
        AdventureInventoryCharDTO adventureInventoryCharDTO = adventureInventoryCharMapper.toDto(adventureInventoryChar);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdventureInventoryCharMockMvc.perform(post("/api/adventure-inventory-chars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureInventoryCharDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureInventoryChar in the database
        List<AdventureInventoryChar> adventureInventoryCharList = adventureInventoryCharRepository.findAll();
        assertThat(adventureInventoryCharList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdventureInventoryChar in Elasticsearch
        verify(mockAdventureInventoryCharSearchRepository, times(0)).save(adventureInventoryChar);
    }


    @Test
    public void getAllAdventureInventoryChars() throws Exception {
        // Initialize the database
        adventureInventoryCharRepository.save(adventureInventoryChar);

        // Get all the adventureInventoryCharList
        restAdventureInventoryCharMockMvc.perform(get("/api/adventure-inventory-chars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureInventoryChar.getId())));
    }
    
    @Test
    public void getAdventureInventoryChar() throws Exception {
        // Initialize the database
        adventureInventoryCharRepository.save(adventureInventoryChar);

        // Get the adventureInventoryChar
        restAdventureInventoryCharMockMvc.perform(get("/api/adventure-inventory-chars/{id}", adventureInventoryChar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adventureInventoryChar.getId()));
    }

    @Test
    public void getNonExistingAdventureInventoryChar() throws Exception {
        // Get the adventureInventoryChar
        restAdventureInventoryCharMockMvc.perform(get("/api/adventure-inventory-chars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAdventureInventoryChar() throws Exception {
        // Initialize the database
        adventureInventoryCharRepository.save(adventureInventoryChar);

        int databaseSizeBeforeUpdate = adventureInventoryCharRepository.findAll().size();

        // Update the adventureInventoryChar
        AdventureInventoryChar updatedAdventureInventoryChar = adventureInventoryCharRepository.findById(adventureInventoryChar.getId()).get();
        AdventureInventoryCharDTO adventureInventoryCharDTO = adventureInventoryCharMapper.toDto(updatedAdventureInventoryChar);

        restAdventureInventoryCharMockMvc.perform(put("/api/adventure-inventory-chars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureInventoryCharDTO)))
            .andExpect(status().isOk());

        // Validate the AdventureInventoryChar in the database
        List<AdventureInventoryChar> adventureInventoryCharList = adventureInventoryCharRepository.findAll();
        assertThat(adventureInventoryCharList).hasSize(databaseSizeBeforeUpdate);
        AdventureInventoryChar testAdventureInventoryChar = adventureInventoryCharList.get(adventureInventoryCharList.size() - 1);

        // Validate the AdventureInventoryChar in Elasticsearch
        verify(mockAdventureInventoryCharSearchRepository, times(1)).save(testAdventureInventoryChar);
    }

    @Test
    public void updateNonExistingAdventureInventoryChar() throws Exception {
        int databaseSizeBeforeUpdate = adventureInventoryCharRepository.findAll().size();

        // Create the AdventureInventoryChar
        AdventureInventoryCharDTO adventureInventoryCharDTO = adventureInventoryCharMapper.toDto(adventureInventoryChar);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdventureInventoryCharMockMvc.perform(put("/api/adventure-inventory-chars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureInventoryCharDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureInventoryChar in the database
        List<AdventureInventoryChar> adventureInventoryCharList = adventureInventoryCharRepository.findAll();
        assertThat(adventureInventoryCharList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdventureInventoryChar in Elasticsearch
        verify(mockAdventureInventoryCharSearchRepository, times(0)).save(adventureInventoryChar);
    }

    @Test
    public void deleteAdventureInventoryChar() throws Exception {
        // Initialize the database
        adventureInventoryCharRepository.save(adventureInventoryChar);

        int databaseSizeBeforeDelete = adventureInventoryCharRepository.findAll().size();

        // Delete the adventureInventoryChar
        restAdventureInventoryCharMockMvc.perform(delete("/api/adventure-inventory-chars/{id}", adventureInventoryChar.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdventureInventoryChar> adventureInventoryCharList = adventureInventoryCharRepository.findAll();
        assertThat(adventureInventoryCharList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdventureInventoryChar in Elasticsearch
        verify(mockAdventureInventoryCharSearchRepository, times(1)).deleteById(adventureInventoryChar.getId());
    }

    @Test
    public void searchAdventureInventoryChar() throws Exception {
        // Initialize the database
        adventureInventoryCharRepository.save(adventureInventoryChar);
        when(mockAdventureInventoryCharSearchRepository.search(queryStringQuery("id:" + adventureInventoryChar.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(adventureInventoryChar), PageRequest.of(0, 1), 1));
        // Search the adventureInventoryChar
        restAdventureInventoryCharMockMvc.perform(get("/api/_search/adventure-inventory-chars?query=id:" + adventureInventoryChar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureInventoryChar.getId())));
    }
}
