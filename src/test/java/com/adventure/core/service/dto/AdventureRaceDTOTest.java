package com.adventure.core.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureRaceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureRaceDTO.class);
        AdventureRaceDTO adventureRaceDTO1 = new AdventureRaceDTO();
        adventureRaceDTO1.setId("id1");
        AdventureRaceDTO adventureRaceDTO2 = new AdventureRaceDTO();
        assertThat(adventureRaceDTO1).isNotEqualTo(adventureRaceDTO2);
        adventureRaceDTO2.setId(adventureRaceDTO1.getId());
        assertThat(adventureRaceDTO1).isEqualTo(adventureRaceDTO2);
        adventureRaceDTO2.setId("id2");
        assertThat(adventureRaceDTO1).isNotEqualTo(adventureRaceDTO2);
        adventureRaceDTO1.setId(null);
        assertThat(adventureRaceDTO1).isNotEqualTo(adventureRaceDTO2);
    }
}
