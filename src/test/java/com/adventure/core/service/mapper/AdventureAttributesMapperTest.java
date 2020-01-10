package com.adventure.core.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdventureAttributesMapperTest {

    private AdventureAttributesMapper adventureAttributesMapper;

    @BeforeEach
    public void setUp() {
        adventureAttributesMapper = new AdventureAttributesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(adventureAttributesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adventureAttributesMapper.fromId(null)).isNull();
    }
}
