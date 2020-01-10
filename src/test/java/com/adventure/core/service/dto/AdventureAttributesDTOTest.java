package com.adventure.core.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureAttributesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureAttributesDTO.class);
        AdventureAttributesDTO adventureAttributesDTO1 = new AdventureAttributesDTO();
        adventureAttributesDTO1.setId("id1");
        AdventureAttributesDTO adventureAttributesDTO2 = new AdventureAttributesDTO();
        assertThat(adventureAttributesDTO1).isNotEqualTo(adventureAttributesDTO2);
        adventureAttributesDTO2.setId(adventureAttributesDTO1.getId());
        assertThat(adventureAttributesDTO1).isEqualTo(adventureAttributesDTO2);
        adventureAttributesDTO2.setId("id2");
        assertThat(adventureAttributesDTO1).isNotEqualTo(adventureAttributesDTO2);
        adventureAttributesDTO1.setId(null);
        assertThat(adventureAttributesDTO1).isNotEqualTo(adventureAttributesDTO2);
    }
}
