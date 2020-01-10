package com.adventure.core.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureAccountCharacterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureAccountCharacterDTO.class);
        AdventureAccountCharacterDTO adventureAccountCharacterDTO1 = new AdventureAccountCharacterDTO();
        adventureAccountCharacterDTO1.setId("id1");
        AdventureAccountCharacterDTO adventureAccountCharacterDTO2 = new AdventureAccountCharacterDTO();
        assertThat(adventureAccountCharacterDTO1).isNotEqualTo(adventureAccountCharacterDTO2);
        adventureAccountCharacterDTO2.setId(adventureAccountCharacterDTO1.getId());
        assertThat(adventureAccountCharacterDTO1).isEqualTo(adventureAccountCharacterDTO2);
        adventureAccountCharacterDTO2.setId("id2");
        assertThat(adventureAccountCharacterDTO1).isNotEqualTo(adventureAccountCharacterDTO2);
        adventureAccountCharacterDTO1.setId(null);
        assertThat(adventureAccountCharacterDTO1).isNotEqualTo(adventureAccountCharacterDTO2);
    }
}
