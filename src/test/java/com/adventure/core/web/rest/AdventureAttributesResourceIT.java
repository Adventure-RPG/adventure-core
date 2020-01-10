package com.adventure.core.web.rest;

import com.adventure.core.AdventureCoreApp;
import com.adventure.core.config.SecurityBeanOverrideConfiguration;
import com.adventure.core.domain.AdventureAttributes;
import com.adventure.core.repository.AdventureAttributesRepository;
import com.adventure.core.repository.search.AdventureAttributesSearchRepository;
import com.adventure.core.service.AdventureAttributesService;
import com.adventure.core.service.dto.AdventureAttributesDTO;
import com.adventure.core.service.mapper.AdventureAttributesMapper;
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

import com.adventure.core.domain.enumeration.AdventureDefenceArmorType;
import com.adventure.core.domain.enumeration.AdventureActiveWeaponAttackDamage;
/**
 * Integration tests for the {@link AdventureAttributesResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, AdventureCoreApp.class})
public class AdventureAttributesResourceIT {

    private static final Long DEFAULT_DEFENCE = 1L;
    private static final Long UPDATED_DEFENCE = 2L;

    private static final AdventureDefenceArmorType DEFAULT_DEFENCE_ARMOR_TYPE = AdventureDefenceArmorType.BASIC;
    private static final AdventureDefenceArmorType UPDATED_DEFENCE_ARMOR_TYPE = AdventureDefenceArmorType.BASIC;

    private static final Long DEFAULT_FIRE_RESISTANCE = 1L;
    private static final Long UPDATED_FIRE_RESISTANCE = 2L;

    private static final Long DEFAULT_EARTH_RESISTANCE = 1L;
    private static final Long UPDATED_EARTH_RESISTANCE = 2L;

    private static final Long DEFAULT_WATER_RESISTANCE = 1L;
    private static final Long UPDATED_WATER_RESISTANCE = 2L;

    private static final Long DEFAULT_WIND_RESISTANCE = 1L;
    private static final Long UPDATED_WIND_RESISTANCE = 2L;

    private static final AdventureActiveWeaponAttackDamage DEFAULT_ACTIVE_WEAPON_ATTACK_DAMAGE = AdventureActiveWeaponAttackDamage.BLUNT;
    private static final AdventureActiveWeaponAttackDamage UPDATED_ACTIVE_WEAPON_ATTACK_DAMAGE = AdventureActiveWeaponAttackDamage.BLUNT;

    private static final Long DEFAULT_ACTIVE_WEAPON_ATTACK_HIT = 1L;
    private static final Long UPDATED_ACTIVE_WEAPON_ATTACK_HIT = 2L;

    private static final Long DEFAULT_ACTIVE_WEAPON_ATTACK_TYPE = 1L;
    private static final Long UPDATED_ACTIVE_WEAPON_ATTACK_TYPE = 2L;

    private static final Long DEFAULT_SIZE = 1L;
    private static final Long UPDATED_SIZE = 2L;

    @Autowired
    private AdventureAttributesRepository adventureAttributesRepository;

    @Autowired
    private AdventureAttributesMapper adventureAttributesMapper;

    @Autowired
    private AdventureAttributesService adventureAttributesService;

    /**
     * This repository is mocked in the com.adventure.core.repository.search test package.
     *
     * @see com.adventure.core.repository.search.AdventureAttributesSearchRepositoryMockConfiguration
     */
    @Autowired
    private AdventureAttributesSearchRepository mockAdventureAttributesSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAdventureAttributesMockMvc;

    private AdventureAttributes adventureAttributes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdventureAttributesResource adventureAttributesResource = new AdventureAttributesResource(adventureAttributesService);
        this.restAdventureAttributesMockMvc = MockMvcBuilders.standaloneSetup(adventureAttributesResource)
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
    public static AdventureAttributes createEntity() {
        AdventureAttributes adventureAttributes = new AdventureAttributes()
            .defence(DEFAULT_DEFENCE)
            .defenceArmorType(DEFAULT_DEFENCE_ARMOR_TYPE)
            .fireResistance(DEFAULT_FIRE_RESISTANCE)
            .earthResistance(DEFAULT_EARTH_RESISTANCE)
            .waterResistance(DEFAULT_WATER_RESISTANCE)
            .windResistance(DEFAULT_WIND_RESISTANCE)
            .activeWeaponAttackDamage(DEFAULT_ACTIVE_WEAPON_ATTACK_DAMAGE)
            .activeWeaponAttackHit(DEFAULT_ACTIVE_WEAPON_ATTACK_HIT)
            .activeWeaponAttackType(DEFAULT_ACTIVE_WEAPON_ATTACK_TYPE)
            .size(DEFAULT_SIZE);
        return adventureAttributes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdventureAttributes createUpdatedEntity() {
        AdventureAttributes adventureAttributes = new AdventureAttributes()
            .defence(UPDATED_DEFENCE)
            .defenceArmorType(UPDATED_DEFENCE_ARMOR_TYPE)
            .fireResistance(UPDATED_FIRE_RESISTANCE)
            .earthResistance(UPDATED_EARTH_RESISTANCE)
            .waterResistance(UPDATED_WATER_RESISTANCE)
            .windResistance(UPDATED_WIND_RESISTANCE)
            .activeWeaponAttackDamage(UPDATED_ACTIVE_WEAPON_ATTACK_DAMAGE)
            .activeWeaponAttackHit(UPDATED_ACTIVE_WEAPON_ATTACK_HIT)
            .activeWeaponAttackType(UPDATED_ACTIVE_WEAPON_ATTACK_TYPE)
            .size(UPDATED_SIZE);
        return adventureAttributes;
    }

    @BeforeEach
    public void initTest() {
        adventureAttributesRepository.deleteAll();
        adventureAttributes = createEntity();
    }

    @Test
    public void createAdventureAttributes() throws Exception {
        int databaseSizeBeforeCreate = adventureAttributesRepository.findAll().size();

        // Create the AdventureAttributes
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);
        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isCreated());

        // Validate the AdventureAttributes in the database
        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeCreate + 1);
        AdventureAttributes testAdventureAttributes = adventureAttributesList.get(adventureAttributesList.size() - 1);
        assertThat(testAdventureAttributes.getDefence()).isEqualTo(DEFAULT_DEFENCE);
        assertThat(testAdventureAttributes.getDefenceArmorType()).isEqualTo(DEFAULT_DEFENCE_ARMOR_TYPE);
        assertThat(testAdventureAttributes.getFireResistance()).isEqualTo(DEFAULT_FIRE_RESISTANCE);
        assertThat(testAdventureAttributes.getEarthResistance()).isEqualTo(DEFAULT_EARTH_RESISTANCE);
        assertThat(testAdventureAttributes.getWaterResistance()).isEqualTo(DEFAULT_WATER_RESISTANCE);
        assertThat(testAdventureAttributes.getWindResistance()).isEqualTo(DEFAULT_WIND_RESISTANCE);
        assertThat(testAdventureAttributes.getActiveWeaponAttackDamage()).isEqualTo(DEFAULT_ACTIVE_WEAPON_ATTACK_DAMAGE);
        assertThat(testAdventureAttributes.getActiveWeaponAttackHit()).isEqualTo(DEFAULT_ACTIVE_WEAPON_ATTACK_HIT);
        assertThat(testAdventureAttributes.getActiveWeaponAttackType()).isEqualTo(DEFAULT_ACTIVE_WEAPON_ATTACK_TYPE);
        assertThat(testAdventureAttributes.getSize()).isEqualTo(DEFAULT_SIZE);

        // Validate the AdventureAttributes in Elasticsearch
        verify(mockAdventureAttributesSearchRepository, times(1)).save(testAdventureAttributes);
    }

    @Test
    public void createAdventureAttributesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adventureAttributesRepository.findAll().size();

        // Create the AdventureAttributes with an existing ID
        adventureAttributes.setId("existing_id");
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureAttributes in the database
        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdventureAttributes in Elasticsearch
        verify(mockAdventureAttributesSearchRepository, times(0)).save(adventureAttributes);
    }


    @Test
    public void checkDefenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setDefence(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDefenceArmorTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setDefenceArmorType(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkFireResistanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setFireResistance(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEarthResistanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setEarthResistance(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkWaterResistanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setWaterResistance(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkWindResistanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setWindResistance(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkActiveWeaponAttackDamageIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setActiveWeaponAttackDamage(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkActiveWeaponAttackHitIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setActiveWeaponAttackHit(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkActiveWeaponAttackTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setActiveWeaponAttackType(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = adventureAttributesRepository.findAll().size();
        // set the field null
        adventureAttributes.setSize(null);

        // Create the AdventureAttributes, which fails.
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        restAdventureAttributesMockMvc.perform(post("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllAdventureAttributes() throws Exception {
        // Initialize the database
        adventureAttributesRepository.save(adventureAttributes);

        // Get all the adventureAttributesList
        restAdventureAttributesMockMvc.perform(get("/api/adventure-attributes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureAttributes.getId())))
            .andExpect(jsonPath("$.[*].defence").value(hasItem(DEFAULT_DEFENCE.intValue())))
            .andExpect(jsonPath("$.[*].defenceArmorType").value(hasItem(DEFAULT_DEFENCE_ARMOR_TYPE.toString())))
            .andExpect(jsonPath("$.[*].fireResistance").value(hasItem(DEFAULT_FIRE_RESISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].earthResistance").value(hasItem(DEFAULT_EARTH_RESISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].waterResistance").value(hasItem(DEFAULT_WATER_RESISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].windResistance").value(hasItem(DEFAULT_WIND_RESISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].activeWeaponAttackDamage").value(hasItem(DEFAULT_ACTIVE_WEAPON_ATTACK_DAMAGE.toString())))
            .andExpect(jsonPath("$.[*].activeWeaponAttackHit").value(hasItem(DEFAULT_ACTIVE_WEAPON_ATTACK_HIT.intValue())))
            .andExpect(jsonPath("$.[*].activeWeaponAttackType").value(hasItem(DEFAULT_ACTIVE_WEAPON_ATTACK_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.intValue())));
    }
    
    @Test
    public void getAdventureAttributes() throws Exception {
        // Initialize the database
        adventureAttributesRepository.save(adventureAttributes);

        // Get the adventureAttributes
        restAdventureAttributesMockMvc.perform(get("/api/adventure-attributes/{id}", adventureAttributes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adventureAttributes.getId()))
            .andExpect(jsonPath("$.defence").value(DEFAULT_DEFENCE.intValue()))
            .andExpect(jsonPath("$.defenceArmorType").value(DEFAULT_DEFENCE_ARMOR_TYPE.toString()))
            .andExpect(jsonPath("$.fireResistance").value(DEFAULT_FIRE_RESISTANCE.intValue()))
            .andExpect(jsonPath("$.earthResistance").value(DEFAULT_EARTH_RESISTANCE.intValue()))
            .andExpect(jsonPath("$.waterResistance").value(DEFAULT_WATER_RESISTANCE.intValue()))
            .andExpect(jsonPath("$.windResistance").value(DEFAULT_WIND_RESISTANCE.intValue()))
            .andExpect(jsonPath("$.activeWeaponAttackDamage").value(DEFAULT_ACTIVE_WEAPON_ATTACK_DAMAGE.toString()))
            .andExpect(jsonPath("$.activeWeaponAttackHit").value(DEFAULT_ACTIVE_WEAPON_ATTACK_HIT.intValue()))
            .andExpect(jsonPath("$.activeWeaponAttackType").value(DEFAULT_ACTIVE_WEAPON_ATTACK_TYPE.intValue()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.intValue()));
    }

    @Test
    public void getNonExistingAdventureAttributes() throws Exception {
        // Get the adventureAttributes
        restAdventureAttributesMockMvc.perform(get("/api/adventure-attributes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAdventureAttributes() throws Exception {
        // Initialize the database
        adventureAttributesRepository.save(adventureAttributes);

        int databaseSizeBeforeUpdate = adventureAttributesRepository.findAll().size();

        // Update the adventureAttributes
        AdventureAttributes updatedAdventureAttributes = adventureAttributesRepository.findById(adventureAttributes.getId()).get();
        updatedAdventureAttributes
            .defence(UPDATED_DEFENCE)
            .defenceArmorType(UPDATED_DEFENCE_ARMOR_TYPE)
            .fireResistance(UPDATED_FIRE_RESISTANCE)
            .earthResistance(UPDATED_EARTH_RESISTANCE)
            .waterResistance(UPDATED_WATER_RESISTANCE)
            .windResistance(UPDATED_WIND_RESISTANCE)
            .activeWeaponAttackDamage(UPDATED_ACTIVE_WEAPON_ATTACK_DAMAGE)
            .activeWeaponAttackHit(UPDATED_ACTIVE_WEAPON_ATTACK_HIT)
            .activeWeaponAttackType(UPDATED_ACTIVE_WEAPON_ATTACK_TYPE)
            .size(UPDATED_SIZE);
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(updatedAdventureAttributes);

        restAdventureAttributesMockMvc.perform(put("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isOk());

        // Validate the AdventureAttributes in the database
        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeUpdate);
        AdventureAttributes testAdventureAttributes = adventureAttributesList.get(adventureAttributesList.size() - 1);
        assertThat(testAdventureAttributes.getDefence()).isEqualTo(UPDATED_DEFENCE);
        assertThat(testAdventureAttributes.getDefenceArmorType()).isEqualTo(UPDATED_DEFENCE_ARMOR_TYPE);
        assertThat(testAdventureAttributes.getFireResistance()).isEqualTo(UPDATED_FIRE_RESISTANCE);
        assertThat(testAdventureAttributes.getEarthResistance()).isEqualTo(UPDATED_EARTH_RESISTANCE);
        assertThat(testAdventureAttributes.getWaterResistance()).isEqualTo(UPDATED_WATER_RESISTANCE);
        assertThat(testAdventureAttributes.getWindResistance()).isEqualTo(UPDATED_WIND_RESISTANCE);
        assertThat(testAdventureAttributes.getActiveWeaponAttackDamage()).isEqualTo(UPDATED_ACTIVE_WEAPON_ATTACK_DAMAGE);
        assertThat(testAdventureAttributes.getActiveWeaponAttackHit()).isEqualTo(UPDATED_ACTIVE_WEAPON_ATTACK_HIT);
        assertThat(testAdventureAttributes.getActiveWeaponAttackType()).isEqualTo(UPDATED_ACTIVE_WEAPON_ATTACK_TYPE);
        assertThat(testAdventureAttributes.getSize()).isEqualTo(UPDATED_SIZE);

        // Validate the AdventureAttributes in Elasticsearch
        verify(mockAdventureAttributesSearchRepository, times(1)).save(testAdventureAttributes);
    }

    @Test
    public void updateNonExistingAdventureAttributes() throws Exception {
        int databaseSizeBeforeUpdate = adventureAttributesRepository.findAll().size();

        // Create the AdventureAttributes
        AdventureAttributesDTO adventureAttributesDTO = adventureAttributesMapper.toDto(adventureAttributes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdventureAttributesMockMvc.perform(put("/api/adventure-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adventureAttributesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdventureAttributes in the database
        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdventureAttributes in Elasticsearch
        verify(mockAdventureAttributesSearchRepository, times(0)).save(adventureAttributes);
    }

    @Test
    public void deleteAdventureAttributes() throws Exception {
        // Initialize the database
        adventureAttributesRepository.save(adventureAttributes);

        int databaseSizeBeforeDelete = adventureAttributesRepository.findAll().size();

        // Delete the adventureAttributes
        restAdventureAttributesMockMvc.perform(delete("/api/adventure-attributes/{id}", adventureAttributes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdventureAttributes> adventureAttributesList = adventureAttributesRepository.findAll();
        assertThat(adventureAttributesList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdventureAttributes in Elasticsearch
        verify(mockAdventureAttributesSearchRepository, times(1)).deleteById(adventureAttributes.getId());
    }

    @Test
    public void searchAdventureAttributes() throws Exception {
        // Initialize the database
        adventureAttributesRepository.save(adventureAttributes);
        when(mockAdventureAttributesSearchRepository.search(queryStringQuery("id:" + adventureAttributes.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(adventureAttributes), PageRequest.of(0, 1), 1));
        // Search the adventureAttributes
        restAdventureAttributesMockMvc.perform(get("/api/_search/adventure-attributes?query=id:" + adventureAttributes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adventureAttributes.getId())))
            .andExpect(jsonPath("$.[*].defence").value(hasItem(DEFAULT_DEFENCE.intValue())))
            .andExpect(jsonPath("$.[*].defenceArmorType").value(hasItem(DEFAULT_DEFENCE_ARMOR_TYPE.toString())))
            .andExpect(jsonPath("$.[*].fireResistance").value(hasItem(DEFAULT_FIRE_RESISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].earthResistance").value(hasItem(DEFAULT_EARTH_RESISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].waterResistance").value(hasItem(DEFAULT_WATER_RESISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].windResistance").value(hasItem(DEFAULT_WIND_RESISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].activeWeaponAttackDamage").value(hasItem(DEFAULT_ACTIVE_WEAPON_ATTACK_DAMAGE.toString())))
            .andExpect(jsonPath("$.[*].activeWeaponAttackHit").value(hasItem(DEFAULT_ACTIVE_WEAPON_ATTACK_HIT.intValue())))
            .andExpect(jsonPath("$.[*].activeWeaponAttackType").value(hasItem(DEFAULT_ACTIVE_WEAPON_ATTACK_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.intValue())));
    }
}
