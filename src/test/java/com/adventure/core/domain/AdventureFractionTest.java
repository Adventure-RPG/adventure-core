package com.adventure.core.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureFractionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureFraction.class);
        AdventureFraction adventureFraction1 = new AdventureFraction();
        adventureFraction1.setId("id1");
        AdventureFraction adventureFraction2 = new AdventureFraction();
        adventureFraction2.setId(adventureFraction1.getId());
        assertThat(adventureFraction1).isEqualTo(adventureFraction2);
        adventureFraction2.setId("id2");
        assertThat(adventureFraction1).isNotEqualTo(adventureFraction2);
        adventureFraction1.setId(null);
        assertThat(adventureFraction1).isNotEqualTo(adventureFraction2);
    }
}
