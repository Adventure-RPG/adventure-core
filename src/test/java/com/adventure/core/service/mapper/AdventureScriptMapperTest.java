package com.adventure.core.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdventureScriptMapperTest {

    private AdventureScriptMapper adventureScriptMapper;

    @BeforeEach
    public void setUp() {
        adventureScriptMapper = new AdventureScriptMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(adventureScriptMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adventureScriptMapper.fromId(null)).isNull();
    }
}
