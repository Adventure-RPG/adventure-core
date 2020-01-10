package com.adventure.core.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdventureCharacteristicMapperTest {

    private AdventureCharacteristicMapper adventureCharacteristicMapper;

    @BeforeEach
    public void setUp() {
        adventureCharacteristicMapper = new AdventureCharacteristicMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(adventureCharacteristicMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adventureCharacteristicMapper.fromId(null)).isNull();
    }
}
