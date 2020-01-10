package com.adventure.core.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adventure.core.web.rest.TestUtil;

public class AdventureCategoryTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdventureCategoryType.class);
        AdventureCategoryType adventureCategoryType1 = new AdventureCategoryType();
        adventureCategoryType1.setId("id1");
        AdventureCategoryType adventureCategoryType2 = new AdventureCategoryType();
        adventureCategoryType2.setId(adventureCategoryType1.getId());
        assertThat(adventureCategoryType1).isEqualTo(adventureCategoryType2);
        adventureCategoryType2.setId("id2");
        assertThat(adventureCategoryType1).isNotEqualTo(adventureCategoryType2);
        adventureCategoryType1.setId(null);
        assertThat(adventureCategoryType1).isNotEqualTo(adventureCategoryType2);
    }
}
