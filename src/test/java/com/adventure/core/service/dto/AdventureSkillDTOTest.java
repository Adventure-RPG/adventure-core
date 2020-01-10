package com.adventure.core.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureSkillDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureSkillDTO.class);
        AdventureSkillDTO adventureSkillDTO1 = new AdventureSkillDTO();
        adventureSkillDTO1.setId("id1");
        AdventureSkillDTO adventureSkillDTO2 = new AdventureSkillDTO();
        assertThat(adventureSkillDTO1).isNotEqualTo(adventureSkillDTO2);
        adventureSkillDTO2.setId(adventureSkillDTO1.getId());
        assertThat(adventureSkillDTO1).isEqualTo(adventureSkillDTO2);
        adventureSkillDTO2.setId("id2");
        assertThat(adventureSkillDTO1).isNotEqualTo(adventureSkillDTO2);
        adventureSkillDTO1.setId(null);
        assertThat(adventureSkillDTO1).isNotEqualTo(adventureSkillDTO2);
    }
}
