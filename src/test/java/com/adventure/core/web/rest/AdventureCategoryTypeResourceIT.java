package com.adventure.core.web.rest;

import com.adventure.core.AdventureCoreApp;
import com.adventure.core.config.SecurityBeanOverrideConfiguration;
import com.adventure.core.domain.AdventureCategoryType;
import com.adventure.core.repository.AdventureCategoryTypeRepository;
import com.adventure.core.repository.search.AdventureCategoryTypeSearchRepository;
import com.adventure.core.service.AdventureCategoryTypeService;
import com.adventure.core.service.dto.AdventureCategoryTypeDTO;
import com.adventure.core.service.mapper.AdventureCategoryTypeMapper;
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
 * Integration tests for the {@link AdventureCategoryTypeResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, AdventureCoreApp.class})
public class AdventureCategoryTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private AdventureCategoryTypeRepository adventureCategoryTypeRepository;

    @Autowired
    private AdventureCategoryTypeMapper adventureCategoryTypeMapper;

    @Autowired
    private AdventureCategoryTypeService adventureCategoryTypeService;

    /**
     * This repository is mocked in the com.adventure.core.repository.search test package.
     *
     * @see com.adventure.core.repository.search.AdventureCategoryTypeSearchRepositoryMockConfiguration
     */
    @Autowired
    private AdventureCategoryTypeSearchRepository mockAdventureCategoryTypeSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAdventureCategoryTypeMockMvc;

    private AdventureCategoryType adventureCategoryType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdventureCategoryTypeResource adventureCategoryTypeResource = new AdventureCategoryTypeResource(adventureCategoryTypeService);
        this.restAdventureCategoryTypeMockMvc = MockMvcBuilders.standaloneSetup(adventureCategoryTypeResource)
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
    public static AdventureCategoryType createEntity() {
        AdventureCategoryType adventureCategoryType = new AdventureCategoryType()
            .name(DEFAULT_NAME)
            .desc(DEFAULT_DESC);
        return adventureCategoryType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdventureCategoryType createUpdatedEntity() {
        AdventureCategoryType adventureCategoryType = new AdventureCategoryType()
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC);
        return adventureCategoryType;
    }

    @BeforeEach
    public void initTest() {
        adventureCategoryTypeRepository.deleteAll();
        adventureCategoryType = createEntity();
    }

    @Test
    public void createAdventureCategoryType() throws Exception {
        int databaseSizeBeforeCreate = adventureCategoryTypeRepository.findAll().size();

        // Create the AdventureCategoryType
        AdventureCategoryTypeDTO adventureCategoryTypeDTO = adventureCategoryTypeMapper.toDto(adventureCategoryType);
        restAdventureCategoryTypeMockMvc.perform(post("/api/adventure-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureCategoryTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the AdventureCategoryType in the database
        List<AdventureCategoryType> adventureCategoryTypeList = adventureCategoryTypeRepository.findAll();
        assertThat(adventureCategoryTypeList).hasSize(databaseSizeBeforeCreate + 1);
        AdventureCategoryType testAdventureCategoryType = adventureCategoryTypeList.get(adventureCategoryTypeList.size() - 1);
        assertThat(testAdventureCategoryType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdventureCategoryType.getDesc()).isEqualTo(DEFAULT_DESC);

        // Validate the AdventureCategoryType in Elasticsearch
        verify(mockAdventureCategoryTypeSearchRepository, times(1)).save(testAdventureCategoryType);
    }

    @Test
    public void createAdventureCategoryTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adventureCategoryTypeRepository.findAll().size();

        // Create the AdventureCategoryType with an existing ID
        adventureCategoryType.setId("existing_id");
        AdventureCategoryTypeDTO adventureCategoryTypeDTO = adventureCategoryTypeMapper.toDto(adventureCategoryType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdventureCategoryTypeMockMvc.perform(post("/api/adventure-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureCategoryTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureCategoryType in the database
        List<AdventureCategoryType> adventureCategoryTypeList = adventureCategoryTypeRepository.findAll();
        assertThat(adventureCategoryTypeList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdventureCategoryType in Elasticsearch
        verify(mockAdventureCategoryTypeSearchRepository, times(0)).save(adventureCategoryType);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureCategoryTypeRepository.findAll().size();
        // set the field null
        adventureCategoryType.setName(null);

        // Create the AdventureCategoryType, which fails.
        AdventureCategoryTypeDTO adventureCategoryTypeDTO = adventureCategoryTypeMapper.toDto(adventureCategoryType);

        restAdventureCategoryTypeMockMvc.perform(post("/api/adventure-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureCategoryTypeDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureCategoryType> adventureCategoryTypeList = adventureCategoryTypeRepository.findAll();
        assertThat(adventureCategoryTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllAdventureCategoryTypes() throws Exception {
        // Initialize the database
        adventureCategoryTypeRepository.save(adventureCategoryType);

        // Get all the adventureCategoryTypeList
        restAdventureCategoryTypeMockMvc.perform(get("/api/adventure-category-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureCategoryType.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }
    
    @Test
    public void getAdventureCategoryType() throws Exception {
        // Initialize the database
        adventureCategoryTypeRepository.save(adventureCategoryType);

        // Get the adventureCategoryType
        restAdventureCategoryTypeMockMvc.perform(get("/api/adventure-category-types/{id}", adventureCategoryType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adventureCategoryType.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC));
    }

    @Test
    public void getNonExistingAdventureCategoryType() throws Exception {
        // Get the adventureCategoryType
        restAdventureCategoryTypeMockMvc.perform(get("/api/adventure-category-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAdventureCategoryType() throws Exception {
        // Initialize the database
        adventureCategoryTypeRepository.save(adventureCategoryType);

        int databaseSizeBeforeUpdate = adventureCategoryTypeRepository.findAll().size();

        // Update the adventureCategoryType
        AdventureCategoryType updatedAdventureCategoryType = adventureCategoryTypeRepository.findById(adventureCategoryType.getId()).get();
        updatedAdventureCategoryType
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC);
        AdventureCategoryTypeDTO adventureCategoryTypeDTO = adventureCategoryTypeMapper.toDto(updatedAdventureCategoryType);

        restAdventureCategoryTypeMockMvc.perform(put("/api/adventure-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureCategoryTypeDTO)))
            .andExpect(status().isOk());

        // Validate the AdventureCategoryType in the database
        List<AdventureCategoryType> adventureCategoryTypeList = adventureCategoryTypeRepository.findAll();
        assertThat(adventureCategoryTypeList).hasSize(databaseSizeBeforeUpdate);
        AdventureCategoryType testAdventureCategoryType = adventureCategoryTypeList.get(adventureCategoryTypeList.size() - 1);
        assertThat(testAdventureCategoryType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdventureCategoryType.getDesc()).isEqualTo(UPDATED_DESC);

        // Validate the AdventureCategoryType in Elasticsearch
        verify(mockAdventureCategoryTypeSearchRepository, times(1)).save(testAdventureCategoryType);
    }

    @Test
    public void updateNonExistingAdventureCategoryType() throws Exception {
        int databaseSizeBeforeUpdate = adventureCategoryTypeRepository.findAll().size();

        // Create the AdventureCategoryType
        AdventureCategoryTypeDTO adventureCategoryTypeDTO = adventureCategoryTypeMapper.toDto(adventureCategoryType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdventureCategoryTypeMockMvc.perform(put("/api/adventure-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureCategoryTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureCategoryType in the database
        List<AdventureCategoryType> adventureCategoryTypeList = adventureCategoryTypeRepository.findAll();
        assertThat(adventureCategoryTypeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdventureCategoryType in Elasticsearch
        verify(mockAdventureCategoryTypeSearchRepository, times(0)).save(adventureCategoryType);
    }

    @Test
    public void deleteAdventureCategoryType() throws Exception {
        // Initialize the database
        adventureCategoryTypeRepository.save(adventureCategoryType);

        int databaseSizeBeforeDelete = adventureCategoryTypeRepository.findAll().size();

        // Delete the adventureCategoryType
        restAdventureCategoryTypeMockMvc.perform(delete("/api/adventure-category-types/{id}", adventureCategoryType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdventureCategoryType> adventureCategoryTypeList = adventureCategoryTypeRepository.findAll();
        assertThat(adventureCategoryTypeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdventureCategoryType in Elasticsearch
        verify(mockAdventureCategoryTypeSearchRepository, times(1)).deleteById(adventureCategoryType.getId());
    }

    @Test
    public void searchAdventureCategoryType() throws Exception {
        // Initialize the database
        adventureCategoryTypeRepository.save(adventureCategoryType);
        when(mockAdventureCategoryTypeSearchRepository.search(queryStringQuery("id:" + adventureCategoryType.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(adventureCategoryType), PageRequest.of(0, 1), 1));
        // Search the adventureCategoryType
        restAdventureCategoryTypeMockMvc.perform(get("/api/_search/adventure-category-types?query=id:" + adventureCategoryType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureCategoryType.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }
}
