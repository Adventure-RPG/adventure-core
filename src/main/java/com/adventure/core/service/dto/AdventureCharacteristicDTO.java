package com.adventure.core.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.adventure.core.domain.AdventureCharacteristic} entity.
 */
public class AdventureCharacteristicDTO implements Serializable {

    private String id;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    private Integer strength;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    private Integer agility;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    private Integer vitality;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    private Integer luck;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    private Integer intelligence;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    private Integer wisdom;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    private Integer charisma;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getVitality() {
        return vitality;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }

    public Integer getLuck() {
        return luck;
    }

    public void setLuck(Integer luck) {
        this.luck = luck;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdventureCharacteristicDTO adventureCharacteristicDTO = (AdventureCharacteristicDTO) o;
        if (adventureCharacteristicDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adventureCharacteristicDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdventureCharacteristicDTO{" +
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
