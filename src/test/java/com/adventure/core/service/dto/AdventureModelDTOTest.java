package com.adventure.core.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureModelDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureModelDTO.class);
        AdventureModelDTO adventureModelDTO1 = new AdventureModelDTO();
        adventureModelDTO1.setId("id1");
        AdventureModelDTO adventureModelDTO2 = new AdventureModelDTO();
        assertThat(adventureModelDTO1).isNotEqualTo(adventureModelDTO2);
        adventureModelDTO2.setId(adventureModelDTO1.getId());
        assertThat(adventureModelDTO1).isEqualTo(adventureModelDTO2);
        adventureModelDTO2.setId("id2");
        assertThat(adventureModelDTO1).isNotEqualTo(adventureModelDTO2);
        adventureModelDTO1.setId(null);
        assertThat(adventureModelDTO1).isNotEqualTo(adventureModelDTO2);
    }
}
