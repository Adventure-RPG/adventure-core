package com.adventure.core.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureRaceOptionsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureRaceOptionsDTO.class);
        AdventureRaceOptionsDTO adventureRaceOptionsDTO1 = new AdventureRaceOptionsDTO();
        adventureRaceOptionsDTO1.setId("id1");
        AdventureRaceOptionsDTO adventureRaceOptionsDTO2 = new AdventureRaceOptionsDTO();
        assertThat(adventureRaceOptionsDTO1).isNotEqualTo(adventureRaceOptionsDTO2);
        adventureRaceOptionsDTO2.setId(adventureRaceOptionsDTO1.getId());
        assertThat(adventureRaceOptionsDTO1).isEqualTo(adventureRaceOptionsDTO2);
        adventureRaceOptionsDTO2.setId("id2");
        assertThat(adventureRaceOptionsDTO1).isNotEqualTo(adventureRaceOptionsDTO2);
        adventureRaceOptionsDTO1.setId(null);
        assertThat(adventureRaceOptionsDTO1).isNotEqualTo(adventureRaceOptionsDTO2);
    }
}
