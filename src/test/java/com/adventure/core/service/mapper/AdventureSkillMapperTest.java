package com.adventure.core.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdventureSkillMapperTest {

    private AdventureSkillMapper adventureSkillMapper;

    @BeforeEach
    public void setUp() {
        adventureSkillMapper = new AdventureSkillMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(adventureSkillMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adventureSkillMapper.fromId(null)).isNull();
    }
}
