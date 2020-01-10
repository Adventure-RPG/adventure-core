package com.adventure.core.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureAttributesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureAttributes.class);
        AdventureAttributes adventureAttributes1 = new AdventureAttributes();
        adventureAttributes1.setId("id1");
        AdventureAttributes adventureAttributes2 = new AdventureAttributes();
        adventureAttributes2.setId(adventureAttributes1.getId());
        assertThat(adventureAttributes1).isEqualTo(adventureAttributes2);
        adventureAttributes2.setId("id2");
        assertThat(adventureAttributes1).isNotEqualTo(adventureAttributes2);
        adventureAttributes1.setId(null);
        assertThat(adventureAttributes1).isNotEqualTo(adventureAttributes2);
    }
}
