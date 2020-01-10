package com.adventure.core.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureCharacteristicTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureCharacteristic.class);
        AdventureCharacteristic adventureCharacteristic1 = new AdventureCharacteristic();
        adventureCharacteristic1.setId("id1");
        AdventureCharacteristic adventureCharacteristic2 = new AdventureCharacteristic();
        adventureCharacteristic2.setId(adventureCharacteristic1.getId());
        assertThat(adventureCharacteristic1).isEqualTo(adventureCharacteristic2);
        adventureCharacteristic2.setId("id2");
        assertThat(adventureCharacteristic1).isNotEqualTo(adventureCharacteristic2);
        adventureCharacteristic1.setId(null);
        assertThat(adventureCharacteristic1).isNotEqualTo(adventureCharacteristic2);
    }
}
