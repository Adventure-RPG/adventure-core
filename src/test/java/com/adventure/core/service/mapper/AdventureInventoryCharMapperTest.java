package com.adventure.core.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdventureInventoryCharMapperTest {

    private AdventureInventoryCharMapper adventureInventoryCharMapper;

    @BeforeEach
    public void setUp() {
        adventureInventoryCharMapper = new AdventureInventoryCharMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(adventureInventoryCharMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adventureInventoryCharMapper.fromId(null)).isNull();
    }
}
