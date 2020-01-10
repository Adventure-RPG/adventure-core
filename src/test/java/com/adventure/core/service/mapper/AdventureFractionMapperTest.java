package com.adventure.core.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdventureFractionMapperTest {

    private AdventureFractionMapper adventureFractionMapper;

    @BeforeEach
    public void setUp() {
        adventureFractionMapper = new AdventureFractionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(adventureFractionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adventureFractionMapper.fromId(null)).isNull();
    }
}
