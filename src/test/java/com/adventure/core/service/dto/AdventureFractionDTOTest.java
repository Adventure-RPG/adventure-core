package com.adventure.core.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureFractionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureFractionDTO.class);
        AdventureFractionDTO adventureFractionDTO1 = new AdventureFractionDTO();
        adventureFractionDTO1.setId("id1");
        AdventureFractionDTO adventureFractionDTO2 = new AdventureFractionDTO();
        assertThat(adventureFractionDTO1).isNotEqualTo(adventureFractionDTO2);
        adventureFractionDTO2.setId(adventureFractionDTO1.getId());
        assertThat(adventureFractionDTO1).isEqualTo(adventureFractionDTO2);
        adventureFractionDTO2.setId("id2");
        assertThat(adventureFractionDTO1).isNotEqualTo(adventureFractionDTO2);
        adventureFractionDTO1.setId(null);
        assertThat(adventureFractionDTO1).isNotEqualTo(adventureFractionDTO2);
    }
}
