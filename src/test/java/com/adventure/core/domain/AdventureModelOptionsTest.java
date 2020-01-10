package com.adventure.core.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureModelOptionsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureModelOptions.class);
        AdventureModelOptions adventureModelOptions1 = new AdventureModelOptions();
        adventureModelOptions1.setId("id1");
        AdventureModelOptions adventureModelOptions2 = new AdventureModelOptions();
        adventureModelOptions2.setId(adventureModelOptions1.getId());
        assertThat(adventureModelOptions1).isEqualTo(adventureModelOptions2);
        adventureModelOptions2.setId("id2");
        assertThat(adventureModelOptions1).isNotEqualTo(adventureModelOptions2);
        adventureModelOptions1.setId(null);
        assertThat(adventureModelOptions1).isNotEqualTo(adventureModelOptions2);
    }
}
