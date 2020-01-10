package com.adventure.core.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A AdventureCharacteristic.
 */
@Document(collection = "adventure_characteristic")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "adventurecharacteristic")
public class AdventureCharacteristic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    @Field("strength")
    private Integer strength;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    @Field("agility")
    private Integer agility;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    @Field("vitality")
    private Integer vitality;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    @Field("luck")
    private Integer luck;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    @Field("intelligence")
    private Integer intelligence;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    @Field("wisdom")
    private Integer wisdom;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    @Field("charisma")
    private Integer charisma;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStrength() {
        return strength;
    }

    public AdventureCharacteristic strength(Integer strength) {
        this.strength = strength;
        return this;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getAgility() {
        return agility;
    }

    public AdventureCharacteristic agility(Integer agility) {
        this.agility = agility;
        return this;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getVitality() {
        return vitality;
    }

    public AdventureCharacteristic vitality(Integer vitality) {
        this.vitality = vitality;
        return this;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }

    public Integer getLuck() {
        return luck;
    }

    public AdventureCharacteristic luck(Integer luck) {
        this.luck = luck;
        return this;
    }

    public void setLuck(Integer luck) {
        this.luck = luck;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public AdventureCharacteristic intelligence(Integer intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public AdventureCharacteristic wisdom(Integer wisdom) {
        this.wisdom = wisdom;
        return this;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public AdventureCharacteristic charisma(Integer charisma) {
        this.charisma = charisma;
        return this;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdventureCharacteristic)) {
            return false;
        }
        return id != null && id.equals(((AdventureCharacteristic) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdventureCharacteristic{" +
            "id=" + getId() +
            ", strength=" + getStrength() +
            ", agility=" + getAgility() +
            ", vitality=" + getVitality() +
            ", luck=" + getLuck() +
            ", intelligence=" + getIntelligence() +
            ", wisdom=" + getWisdom() +
            ", charisma=" + getCharisma() +
            "}";
    }
}
