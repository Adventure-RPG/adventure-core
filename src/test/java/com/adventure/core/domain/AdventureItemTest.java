package com.adventure.core.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureItem.class);
        AdventureItem adventureItem1 = new AdventureItem();
        adventureItem1.setId("id1");
        AdventureItem adventureItem2 = new AdventureItem();
        adventureItem2.setId(adventureItem1.getId());
        assertThat(adventureItem1).isEqualTo(adventureItem2);
        adventureItem2.setId("id2");
        assertThat(adventureItem1).isNotEqualTo(adventureItem2);
        adventureItem1.setId(null);
        assertThat(adventureItem1).isNotEqualTo(adventureItem2);
    }
}
