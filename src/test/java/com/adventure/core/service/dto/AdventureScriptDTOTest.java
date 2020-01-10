package com.adventure.core.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureScriptDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureScriptDTO.class);
        AdventureScriptDTO adventureScriptDTO1 = new AdventureScriptDTO();
        adventureScriptDTO1.setId("id1");
        AdventureScriptDTO adventureScriptDTO2 = new AdventureScriptDTO();
        assertThat(adventureScriptDTO1).isNotEqualTo(adventureScriptDTO2);
        adventureScriptDTO2.setId(adventureScriptDTO1.getId());
        assertThat(adventureScriptDTO1).isEqualTo(adventureScriptDTO2);
        adventureScriptDTO2.setId("id2");
        assertThat(adventureScriptDTO1).isNotEqualTo(adventureScriptDTO2);
        adventureScriptDTO1.setId(null);
        assertThat(adventureScriptDTO1).isNotEqualTo(adventureScriptDTO2);
    }
}
