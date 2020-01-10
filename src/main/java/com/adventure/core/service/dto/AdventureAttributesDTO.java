package com.adventure.core.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.adventure.core.domain.enumeration.AdventureDefenceArmorType;
import com.adventure.core.domain.enumeration.AdventureActiveWeaponAttackDamage;

/**
 * A DTO for the {@link com.adventure.core.domain.AdventureAttributes} entity.
 */
public class AdventureAttributesDTO implements Serializable {

    private String id;

    @NotNull
    private Long defence;

    @NotNull
    private AdventureDefenceArmorType defenceArmorType;

    @NotNull
    private Long fireResistance;

    @NotNull
    private Long earthResistance;

    @NotNull
    private Long waterResistance;

    @NotNull
    private Long windResistance;

    @NotNull
    private AdventureActiveWeaponAttackDamage activeWeaponAttackDamage;

    @NotNull
    private Long activeWeaponAttackHit;

    @NotNull
    private Long activeWeaponAttackType;

    @NotNull
    private Long size;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDefence() {
        return defence;
    }

    public void setDefence(Long defence) {
        this.defence = defence;
    }

    public AdventureDefenceArmorType getDefenceArmorType() {
        return defenceArmorType;
    }

    public void setDefenceArmorType(AdventureDefenceArmorType defenceArmorType) {
        this.defenceArmorType = defenceArmorType;
    }

    public Long getFireResistance() {
        return fireResistance;
    }

    public void setFireResistance(Long fireResistance) {
        this.fireResistance = fireResistance;
    }

    public Long getEarthResistance() {
        return earthResistance;
    }

    public void setEarthResistance(Long earthResistance) {
        this.earthResistance = earthResistance;
    }

    public Long getWaterResistance() {
        return waterResistance;
    }

    public void setWaterResistance(Long waterResistance) {
        this.waterResistance = waterResistance;
    }

    public Long getWindResistance() {
        return windResistance;
    }

    public void setWindResistance(Long windResistance) {
        this.windResistance = windResistance;
    }

    public AdventureActiveWeaponAttackDamage getActiveWeaponAttackDamage() {
        return activeWeaponAttackDamage;
    }

    public void setActiveWeaponAttackDamage(AdventureActiveWeaponAttackDamage activeWeaponAttackDamage) {
        this.activeWeaponAttackDamage = activeWeaponAttackDamage;
    }

    public Long getActiveWeaponAttackHit() {
        return activeWeaponAttackHit;
    }

    public void setActiveWeaponAttackHit(Long activeWeaponAttackHit) {
        this.activeWeaponAttackHit = activeWeaponAttackHit;
    }

    public Long getActiveWeaponAttackType() {
        return activeWeaponAttackType;
    }

    public void setActiveWeaponAttackType(Long activeWeaponAttackType) {
        this.activeWeaponAttackType = activeWeaponAttackType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdventureAttributesDTO adventureAttributesDTO = (AdventureAttributesDTO) o;
        if (adventureAttributesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adventureAttributesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdventureAttributesDTO{" +
            "id=" + getId() +
            ", defence=" + getDefence() +
            ", defenceArmorType='" + getDefenceArmorType() + "'" +
            ", fireResistance=" + getFireResistance() +
            ", earthResistance=" + getEarthResistance() +
            ", waterResistance=" + getWaterResistance() +
            ", windResistance=" + getWindResistance() +
            ", activeWeaponAttackDamage='" + getActiveWeaponAttackDamage() + "'" +
            ", activeWeaponAttackHit=" + getActiveWeaponAttackHit() +
            ", activeWeaponAttackType=" + getActiveWeaponAttackType() +
            ", size=" + getSize() +
            "}";
    }
}
