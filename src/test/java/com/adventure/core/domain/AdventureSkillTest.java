package com.adventure.core.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureSkillTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureSkill.class);
        AdventureSkill adventureSkill1 = new AdventureSkill();
        adventureSkill1.setId("id1");
        AdventureSkill adventureSkill2 = new AdventureSkill();
        adventureSkill2.setId(adventureSkill1.getId());
        assertThat(adventureSkill1).isEqualTo(adventureSkill2);
        adventureSkill2.setId("id2");
        assertThat(adventureSkill1).isNotEqualTo(adventureSkill2);
        adventureSkill1.setId(null);
        assertThat(adventureSkill1).isNotEqualTo(adventureSkill2);
    }
}
