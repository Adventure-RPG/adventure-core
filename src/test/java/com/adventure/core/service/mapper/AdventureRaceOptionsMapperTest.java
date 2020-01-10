package com.adventure.core.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdventureRaceOptionsMapperTest {

    private AdventureRaceOptionsMapper adventureRaceOptionsMapper;

    @BeforeEach
    public void setUp() {
        adventureRaceOptionsMapper = new AdventureRaceOptionsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(adventureRaceOptionsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adventureRaceOptionsMapper.fromId(null)).isNull();
    }
}
