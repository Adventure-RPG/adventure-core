package com.adventure.core.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdventureItemMapperTest {

    private AdventureItemMapper adventureItemMapper;

    @BeforeEach
    public void setUp() {
        adventureItemMapper = new AdventureItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(adventureItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adventureItemMapper.fromId(null)).isNull();
    }
}
