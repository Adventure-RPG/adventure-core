package com.adventure.core.web.rest;

import com.adventure.core.AdventureCoreApp;
import com.adventure.core.config.SecurityBeanOverrideConfiguration;
import com.adventure.core.domain.AdventureRace;
import com.adventure.core.repository.AdventureRaceRepository;
import com.adventure.core.repository.search.AdventureRaceSearchRepository;
import com.adventure.core.service.AdventureRaceService;
import com.adventure.core.service.dto.AdventureRaceDTO;
import com.adventure.core.service.mapper.AdventureRaceMapper;
import com.adventure.core.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;


import java.util.ArrayList;
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
 * Integration tests for the {@link AdventureRaceResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, AdventureCoreApp.class})
public class AdventureRaceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private AdventureRaceRepository adventureRaceRepository;

    @Mock
    private AdventureRaceRepository adventureRaceRepositoryMock;

    @Autowired
    private AdventureRaceMapper adventureRaceMapper;

    @Mock
    private AdventureRaceService adventureRaceServiceMock;

    @Autowired
    private AdventureRaceService adventureRaceService;

    /**
     * This repository is mocked in the com.adventure.core.repository.search test package.
     *
     * @see com.adventure.core.repository.search.AdventureRaceSearchRepositoryMockConfiguration
     */
    @Autowired
    private AdventureRaceSearchRepository mockAdventureRaceSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAdventureRaceMockMvc;

    private AdventureRace adventureRace;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdventureRaceResource adventureRaceResource = new AdventureRaceResource(adventureRaceService);
        this.restAdventureRaceMockMvc = MockMvcBuilders.standaloneSetup(adventureRaceResource)
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
    public static AdventureRace createEntity() {
        AdventureRace adventureRace = new AdventureRace()
            .name(DEFAULT_NAME)
            .desc(DEFAULT_DESC)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return adventureRace;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdventureRace createUpdatedEntity() {
        AdventureRace adventureRace = new AdventureRace()
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return adventureRace;
    }

    @BeforeEach
    public void initTest() {
        adventureRaceRepository.deleteAll();
        adventureRace = createEntity();
    }

    @Test
    public void createAdventureRace() throws Exception {
        int databaseSizeBeforeCreate = adventureRaceRepository.findAll().size();

        // Create the AdventureRace
        AdventureRaceDTO adventureRaceDTO = adventureRaceMapper.toDto(adventureRace);
        restAdventureRaceMockMvc.perform(post("/api/adventure-races")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureRaceDTO)))
            .andExpect(status().isCreated());

        // Validate the AdventureRace in the database
        List<AdventureRace> adventureRaceList = adventureRaceRepository.findAll();
        assertThat(adventureRaceList).hasSize(databaseSizeBeforeCreate + 1);
        AdventureRace testAdventureRace = adventureRaceList.get(adventureRaceList.size() - 1);
        assertThat(testAdventureRace.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdventureRace.getDesc()).isEqualTo(DEFAULT_DESC);
        assertThat(testAdventureRace.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testAdventureRace.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);

        // Validate the AdventureRace in Elasticsearch
        verify(mockAdventureRaceSearchRepository, times(1)).save(testAdventureRace);
    }

    @Test
    public void createAdventureRaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adventureRaceRepository.findAll().size();

        // Create the AdventureRace with an existing ID
        adventureRace.setId("existing_id");
        AdventureRaceDTO adventureRaceDTO = adventureRaceMapper.toDto(adventureRace);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdventureRaceMockMvc.perform(post("/api/adventure-races")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureRaceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureRace in the database
        List<AdventureRace> adventureRaceList = adventureRaceRepository.findAll();
        assertThat(adventureRaceList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdventureRace in Elasticsearch
        verify(mockAdventureRaceSearchRepository, times(0)).save(adventureRace);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureRaceRepository.findAll().size();
        // set the field null
        adventureRace.setName(null);

        // Create the AdventureRace, which fails.
        AdventureRaceDTO adventureRaceDTO = adventureRaceMapper.toDto(adventureRace);

        restAdventureRaceMockMvc.perform(post("/api/adventure-races")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureRaceDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureRace> adventureRaceList = adventureRaceRepository.findAll();
        assertThat(adventureRaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureRaceRepository.findAll().size();
        // set the field null
        adventureRace.setDesc(null);

        // Create the AdventureRace, which fails.
        AdventureRaceDTO adventureRaceDTO = adventureRaceMapper.toDto(adventureRace);

        restAdventureRaceMockMvc.perform(post("/api/adventure-races")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureRaceDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureRace> adventureRaceList = adventureRaceRepository.findAll();
        assertThat(adventureRaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllAdventureRaces() throws Exception {
        // Initialize the database
        adventureRaceRepository.save(adventureRace);

        // Get all the adventureRaceList
        restAdventureRaceMockMvc.perform(get("/api/adventure-races?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureRace.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllAdventureRacesWithEagerRelationshipsIsEnabled() throws Exception {
        AdventureRaceResource adventureRaceResource = new AdventureRaceResource(adventureRaceServiceMock);
        when(adventureRaceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restAdventureRaceMockMvc = MockMvcBuilders.standaloneSetup(adventureRaceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAdventureRaceMockMvc.perform(get("/api/adventure-races?eagerload=true"))
        .andExpect(status().isOk());

        verify(adventureRaceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllAdventureRacesWithEagerRelationshipsIsNotEnabled() throws Exception {
        AdventureRaceResource adventureRaceResource = new AdventureRaceResource(adventureRaceServiceMock);
            when(adventureRaceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restAdventureRaceMockMvc = MockMvcBuilders.standaloneSetup(adventureRaceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAdventureRaceMockMvc.perform(get("/api/adventure-races?eagerload=true"))
        .andExpect(status().isOk());

            verify(adventureRaceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getAdventureRace() throws Exception {
        // Initialize the database
        adventureRaceRepository.save(adventureRace);

        // Get the adventureRace
        restAdventureRaceMockMvc.perform(get("/api/adventure-races/{id}", adventureRace.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adventureRace.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }

    @Test
    public void getNonExistingAdventureRace() throws Exception {
        // Get the adventureRace
        restAdventureRaceMockMvc.perform(get("/api/adventure-races/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAdventureRace() throws Exception {
        // Initialize the database
        adventureRaceRepository.save(adventureRace);

        int databaseSizeBeforeUpdate = adventureRaceRepository.findAll().size();

        // Update the adventureRace
        AdventureRace updatedAdventureRace = adventureRaceRepository.findById(adventureRace.getId()).get();
        updatedAdventureRace
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        AdventureRaceDTO adventureRaceDTO = adventureRaceMapper.toDto(updatedAdventureRace);

        restAdventureRaceMockMvc.perform(put("/api/adventure-races")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureRaceDTO)))
            .andExpect(status().isOk());

        // Validate the AdventureRace in the database
        List<AdventureRace> adventureRaceList = adventureRaceRepository.findAll();
        assertThat(adventureRaceList).hasSize(databaseSizeBeforeUpdate);
        AdventureRace testAdventureRace = adventureRaceList.get(adventureRaceList.size() - 1);
        assertThat(testAdventureRace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdventureRace.getDesc()).isEqualTo(UPDATED_DESC);
        assertThat(testAdventureRace.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testAdventureRace.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);

        // Validate the AdventureRace in Elasticsearch
        verify(mockAdventureRaceSearchRepository, times(1)).save(testAdventureRace);
    }

    @Test
    public void updateNonExistingAdventureRace() throws Exception {
        int databaseSizeBeforeUpdate = adventureRaceRepository.findAll().size();

        // Create the AdventureRace
        AdventureRaceDTO adventureRaceDTO = adventureRaceMapper.toDto(adventureRace);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdventureRaceMockMvc.perform(put("/api/adventure-races")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureRaceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureRace in the database
        List<AdventureRace> adventureRaceList = adventureRaceRepository.findAll();
        assertThat(adventureRaceList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdventureRace in Elasticsearch
        verify(mockAdventureRaceSearchRepository, times(0)).save(adventureRace);
    }

    @Test
    public void deleteAdventureRace() throws Exception {
        // Initialize the database
        adventureRaceRepository.save(adventureRace);

        int databaseSizeBeforeDelete = adventureRaceRepository.findAll().size();

        // Delete the adventureRace
        restAdventureRaceMockMvc.perform(delete("/api/adventure-races/{id}", adventureRace.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdventureRace> adventureRaceList = adventureRaceRepository.findAll();
        assertThat(adventureRaceList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdventureRace in Elasticsearch
        verify(mockAdventureRaceSearchRepository, times(1)).deleteById(adventureRace.getId());
    }

    @Test
    public void searchAdventureRace() throws Exception {
        // Initialize the database
        adventureRaceRepository.save(adventureRace);
        when(mockAdventureRaceSearchRepository.search(queryStringQuery("id:" + adventureRace.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(adventureRace), PageRequest.of(0, 1), 1));
        // Search the adventureRace
        restAdventureRaceMockMvc.perform(get("/api/_search/adventure-races?query=id:" + adventureRace.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureRace.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
}
