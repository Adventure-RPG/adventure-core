package com.adventure.core.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdventureModelMapperTest {

    private AdventureModelMapper adventureModelMapper;

    @BeforeEach
    public void setUp() {
        adventureModelMapper = new AdventureModelMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(adventureModelMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adventureModelMapper.fromId(null)).isNull();
    }
}
