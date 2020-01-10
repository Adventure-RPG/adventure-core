package com.adventure.core.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureInventoryCharTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureInventoryChar.class);
        AdventureInventoryChar adventureInventoryChar1 = new AdventureInventoryChar();
        adventureInventoryChar1.setId("id1");
        AdventureInventoryChar adventureInventoryChar2 = new AdventureInventoryChar();
        adventureInventoryChar2.setId(adventureInventoryChar1.getId());
        assertThat(adventureInventoryChar1).isEqualTo(adventureInventoryChar2);
        adventureInventoryChar2.setId("id2");
        assertThat(adventureInventoryChar1).isNotEqualTo(adventureInventoryChar2);
        adventureInventoryChar1.setId(null);
        assertThat(adventureInventoryChar1).isNotEqualTo(adventureInventoryChar2);
    }
}
