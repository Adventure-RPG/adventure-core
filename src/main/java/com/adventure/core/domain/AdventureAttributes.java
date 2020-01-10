package com.adventure.core.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import com.adventure.core.domain.enumeration.AdventureDefenceArmorType;

import com.adventure.core.domain.enumeration.AdventureActiveWeaponAttackDamage;

/**
 * A AdventureAttributes.
 */
@Document(collection = "adventure_attributes")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "adventureattributes")
public class AdventureAttributes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @NotNull
    @Field("defence")
    private Long defence;

    @NotNull
    @Field("defence_armor_type")
    private AdventureDefenceArmorType defenceArmorType;

    @NotNull
    @Field("fire_resistance")
    private Long fireResistance;

    @NotNull
    @Field("earth_resistance")
    private Long earthResistance;

    @NotNull
    @Field("water_resistance")
    private Long waterResistance;

    @NotNull
    @Field("wind_resistance")
    private Long windResistance;

    @NotNull
    @Field("active_weapon_attack_damage")
    private AdventureActiveWeaponAttackDamage activeWeaponAttackDamage;

    @NotNull
    @Field("active_weapon_attack_hit")
    private Long activeWeaponAttackHit;

    @NotNull
    @Field("active_weapon_attack_type")
    private Long activeWeaponAttackType;

    @NotNull
    @Field("size")
    private Long size;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDefence() {
        return defence;
    }

    public AdventureAttributes defence(Long defence) {
        this.defence = defence;
        return this;
    }

    public void setDefence(Long defence) {
        this.defence = defence;
    }

    public AdventureDefenceArmorType getDefenceArmorType() {
        return defenceArmorType;
    }

    public AdventureAttributes defenceArmorType(AdventureDefenceArmorType defenceArmorType) {
        this.defenceArmorType = defenceArmorType;
        return this;
    }

    public void setDefenceArmorType(AdventureDefenceArmorType defenceArmorType) {
        this.defenceArmorType = defenceArmorType;
    }

    public Long getFireResistance() {
        return fireResistance;
    }

    public AdventureAttributes fireResistance(Long fireResistance) {
        this.fireResistance = fireResistance;
        return this;
    }

    public void setFireResistance(Long fireResistance) {
        this.fireResistance = fireResistance;
    }

    public Long getEarthResistance() {
        return earthResistance;
    }

    public AdventureAttributes earthResistance(Long earthResistance) {
        this.earthResistance = earthResistance;
        return this;
    }

    public void setEarthResistance(Long earthResistance) {
        this.earthResistance = earthResistance;
    }

    public Long getWaterResistance() {
        return waterResistance;
    }

    public AdventureAttributes waterResistance(Long waterResistance) {
        this.waterResistance = waterResistance;
        return this;
    }

    public void setWaterResistance(Long waterResistance) {
        this.waterResistance = waterResistance;
    }

    public Long getWindResistance() {
        return windResistance;
    }

    public AdventureAttributes windResistance(Long windResistance) {
        this.windResistance = windResistance;
        return this;
    }

    public void setWindResistance(Long windResistance) {
        this.windResistance = windResistance;
    }

    public AdventureActiveWeaponAttackDamage getActiveWeaponAttackDamage() {
        return activeWeaponAttackDamage;
    }

    public AdventureAttributes activeWeaponAttackDamage(AdventureActiveWeaponAttackDamage activeWeaponAttackDamage) {
        this.activeWeaponAttackDamage = activeWeaponAttackDamage;
        return this;
    }

    public void setActiveWeaponAttackDamage(AdventureActiveWeaponAttackDamage activeWeaponAttackDamage) {
        this.activeWeaponAttackDamage = activeWeaponAttackDamage;
    }

    public Long getActiveWeaponAttackHit() {
        return activeWeaponAttackHit;
    }

    public AdventureAttributes activeWeaponAttackHit(Long activeWeaponAttackHit) {
        this.activeWeaponAttackHit = activeWeaponAttackHit;
        return this;
    }

    public void setActiveWeaponAttackHit(Long activeWeaponAttackHit) {
        this.activeWeaponAttackHit = activeWeaponAttackHit;
    }

    public Long getActiveWeaponAttackType() {
        return activeWeaponAttackType;
    }

    public AdventureAttributes activeWeaponAttackType(Long activeWeaponAttackType) {
        this.activeWeaponAttackType = activeWeaponAttackType;
        return this;
    }

    public void setActiveWeaponAttackType(Long activeWeaponAttackType) {
        this.activeWeaponAttackType = activeWeaponAttackType;
    }

    public Long getSize() {
        return size;
    }

    public AdventureAttributes size(Long size) {
        this.size = size;
        return this;
    }

    public void setSize(Long size) {
        this.size = size;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdventureAttributes)) {
            return false;
        }
        return id != null && id.equals(((AdventureAttributes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdventureAttributes{" +
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
