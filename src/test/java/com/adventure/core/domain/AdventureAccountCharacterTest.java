package com.adventure.core.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureAccountCharacterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureAccountCharacter.class);
        AdventureAccountCharacter adventureAccountCharacter1 = new AdventureAccountCharacter();
        adventureAccountCharacter1.setId("id1");
        AdventureAccountCharacter adventureAccountCharacter2 = new AdventureAccountCharacter();
        adventureAccountCharacter2.setId(adventureAccountCharacter1.getId());
        assertThat(adventureAccountCharacter1).isEqualTo(adventureAccountCharacter2);
        adventureAccountCharacter2.setId("id2");
        assertThat(adventureAccountCharacter1).isNotEqualTo(adventureAccountCharacter2);
        adventureAccountCharacter1.setId(null);
        assertThat(adventureAccountCharacter1).isNotEqualTo(adventureAccountCharacter2);
    }
}
