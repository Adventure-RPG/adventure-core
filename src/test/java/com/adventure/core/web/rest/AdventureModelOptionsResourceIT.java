package com.adventure.core.web.rest;

import com.adventure.core.AdventureCoreApp;
import com.adventure.core.config.SecurityBeanOverrideConfiguration;
import com.adventure.core.domain.AdventureModelOptions;
import com.adventure.core.repository.AdventureModelOptionsRepository;
import com.adventure.core.repository.search.AdventureModelOptionsSearchRepository;
import com.adventure.core.service.AdventureModelOptionsService;
import com.adventure.core.service.dto.AdventureModelOptionsDTO;
import com.adventure.core.service.mapper.AdventureModelOptionsMapper;
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
 * Integration tests for the {@link AdventureModelOptionsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, AdventureCoreApp.class})
public class AdventureModelOptionsResourceIT {

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    @Autowired
    private AdventureModelOptionsRepository adventureModelOptionsRepository;

    @Autowired
    private AdventureModelOptionsMapper adventureModelOptionsMapper;

    @Autowired
    private AdventureModelOptionsService adventureModelOptionsService;

    /**
     * This repository is mocked in the com.adventure.core.repository.search test package.
     *
     * @see com.adventure.core.repository.search.AdventureModelOptionsSearchRepositoryMockConfiguration
     */
    @Autowired
    private AdventureModelOptionsSearchRepository mockAdventureModelOptionsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAdventureModelOptionsMockMvc;

    private AdventureModelOptions adventureModelOptions;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdventureModelOptionsResource adventureModelOptionsResource = new AdventureModelOptionsResource(adventureModelOptionsService);
        this.restAdventureModelOptionsMockMvc = MockMvcBuilders.standaloneSetup(adventureModelOptionsResource)
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
    public static AdventureModelOptions createEntity() {
        AdventureModelOptions adventureModelOptions = new AdventureModelOptions()
            .color(DEFAULT_COLOR);
        return adventureModelOptions;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdventureModelOptions createUpdatedEntity() {
        AdventureModelOptions adventureModelOptions = new AdventureModelOptions()
            .color(UPDATED_COLOR);
        return adventureModelOptions;
    }

    @BeforeEach
    public void initTest() {
        adventureModelOptionsRepository.deleteAll();
        adventureModelOptions = createEntity();
    }

    @Test
    public void createAdventureModelOptions() throws Exception {
        int databaseSizeBeforeCreate = adventureModelOptionsRepository.findAll().size();

        // Create the AdventureModelOptions
        AdventureModelOptionsDTO adventureModelOptionsDTO = adventureModelOptionsMapper.toDto(adventureModelOptions);
        restAdventureModelOptionsMockMvc.perform(post("/api/adventure-model-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureModelOptionsDTO)))
            .andExpect(status().isCreated());

        // Validate the AdventureModelOptions in the database
        List<AdventureModelOptions> adventureModelOptionsList = adventureModelOptionsRepository.findAll();
        assertThat(adventureModelOptionsList).hasSize(databaseSizeBeforeCreate + 1);
        AdventureModelOptions testAdventureModelOptions = adventureModelOptionsList.get(adventureModelOptionsList.size() - 1);
        assertThat(testAdventureModelOptions.getColor()).isEqualTo(DEFAULT_COLOR);

        // Validate the AdventureModelOptions in Elasticsearch
        verify(mockAdventureModelOptionsSearchRepository, times(1)).save(testAdventureModelOptions);
    }

    @Test
    public void createAdventureModelOptionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adventureModelOptionsRepository.findAll().size();

        // Create the AdventureModelOptions with an existing ID
        adventureModelOptions.setId("existing_id");
        AdventureModelOptionsDTO adventureModelOptionsDTO = adventureModelOptionsMapper.toDto(adventureModelOptions);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdventureModelOptionsMockMvc.perform(post("/api/adventure-model-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureModelOptionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureModelOptions in the database
        List<AdventureModelOptions> adventureModelOptionsList = adventureModelOptionsRepository.findAll();
        assertThat(adventureModelOptionsList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdventureModelOptions in Elasticsearch
        verify(mockAdventureModelOptionsSearchRepository, times(0)).save(adventureModelOptions);
    }


    @Test
    public void getAllAdventureModelOptions() throws Exception {
        // Initialize the database
        adventureModelOptionsRepository.save(adventureModelOptions);

        // Get all the adventureModelOptionsList
        restAdventureModelOptionsMockMvc.perform(get("/api/adventure-model-options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureModelOptions.getId())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)));
    }
    
    @Test
    public void getAdventureModelOptions() throws Exception {
        // Initialize the database
        adventureModelOptionsRepository.save(adventureModelOptions);

        // Get the adventureModelOptions
        restAdventureModelOptionsMockMvc.perform(get("/api/adventure-model-options/{id}", adventureModelOptions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adventureModelOptions.getId()))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR));
    }

    @Test
    public void getNonExistingAdventureModelOptions() throws Exception {
        // Get the adventureModelOptions
        restAdventureModelOptionsMockMvc.perform(get("/api/adventure-model-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAdventureModelOptions() throws Exception {
        // Initialize the database
        adventureModelOptionsRepository.save(adventureModelOptions);

        int databaseSizeBeforeUpdate = adventureModelOptionsRepository.findAll().size();

        // Update the adventureModelOptions
        AdventureModelOptions updatedAdventureModelOptions = adventureModelOptionsRepository.findById(adventureModelOptions.getId()).get();
        updatedAdventureModelOptions
            .color(UPDATED_COLOR);
        AdventureModelOptionsDTO adventureModelOptionsDTO = adventureModelOptionsMapper.toDto(updatedAdventureModelOptions);

        restAdventureModelOptionsMockMvc.perform(put("/api/adventure-model-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureModelOptionsDTO)))
            .andExpect(status().isOk());

        // Validate the AdventureModelOptions in the database
        List<AdventureModelOptions> adventureModelOptionsList = adventureModelOptionsRepository.findAll();
        assertThat(adventureModelOptionsList).hasSize(databaseSizeBeforeUpdate);
        AdventureModelOptions testAdventureModelOptions = adventureModelOptionsList.get(adventureModelOptionsList.size() - 1);
        assertThat(testAdventureModelOptions.getColor()).isEqualTo(UPDATED_COLOR);

        // Validate the AdventureModelOptions in Elasticsearch
        verify(mockAdventureModelOptionsSearchRepository, times(1)).save(testAdventureModelOptions);
    }

    @Test
    public void updateNonExistingAdventureModelOptions() throws Exception {
        int databaseSizeBeforeUpdate = adventureModelOptionsRepository.findAll().size();

        // Create the AdventureModelOptions
        AdventureModelOptionsDTO adventureModelOptionsDTO = adventureModelOptionsMapper.toDto(adventureModelOptions);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdventureModelOptionsMockMvc.perform(put("/api/adventure-model-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureModelOptionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureModelOptions in the database
        List<AdventureModelOptions> adventureModelOptionsList = adventureModelOptionsRepository.findAll();
        assertThat(adventureModelOptionsList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdventureModelOptions in Elasticsearch
        verify(mockAdventureModelOptionsSearchRepository, times(0)).save(adventureModelOptions);
    }

    @Test
    public void deleteAdventureModelOptions() throws Exception {
        // Initialize the database
        adventureModelOptionsRepository.save(adventureModelOptions);

        int databaseSizeBeforeDelete = adventureModelOptionsRepository.findAll().size();

        // Delete the adventureModelOptions
        restAdventureModelOptionsMockMvc.perform(delete("/api/adventure-model-options/{id}", adventureModelOptions.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdventureModelOptions> adventureModelOptionsList = adventureModelOptionsRepository.findAll();
        assertThat(adventureModelOptionsList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdventureModelOptions in Elasticsearch
        verify(mockAdventureModelOptionsSearchRepository, times(1)).deleteById(adventureModelOptions.getId());
    }

    @Test
    public void searchAdventureModelOptions() throws Exception {
        // Initialize the database
        adventureModelOptionsRepository.save(adventureModelOptions);
        when(mockAdventureModelOptionsSearchRepository.search(queryStringQuery("id:" + adventureModelOptions.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(adventureModelOptions), PageRequest.of(0, 1), 1));
        // Search the adventureModelOptions
        restAdventureModelOptionsMockMvc.perform(get("/api/_search/adventure-model-options?query=id:" + adventureModelOptions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureModelOptions.getId())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)));
    }
}
