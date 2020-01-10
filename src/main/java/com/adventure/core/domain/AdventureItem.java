package com.adventure.core.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import com.adventure.core.domain.enumeration.AdventureEquipmentSlot;

/**
 * A AdventureItem.
 */
@Document(collection = "adventure_item")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "adventureitem")
public class AdventureItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @Field("is_equipment")
    private Boolean isEquipment;

    @Field("equipment_slot")
    private AdventureEquipmentSlot equipmentSlot;

    @Field("price")
    private Long price;

    @Field("weight")
    private Long weight;

    @DBRef
    @Field("adventureAttributes")
    private AdventureAttributes adventureAttributes;

    @DBRef
    @Field("adventureInventoryChar")
    @JsonIgnoreProperties("adventureItems")
    private AdventureInventoryChar adventureInventoryChar;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean isIsEquipment() {
        return isEquipment;
    }

    public AdventureItem isEquipment(Boolean isEquipment) {
        this.isEquipment = isEquipment;
        return this;
    }

    public void setIsEquipment(Boolean isEquipment) {
        this.isEquipment = isEquipment;
    }

    public AdventureEquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    public AdventureItem equipmentSlot(AdventureEquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
        return this;
    }

    public void setEquipmentSlot(AdventureEquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public Long getPrice() {
        return price;
    }

    public AdventureItem price(Long price) {
        this.price = price;
        return this;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getWeight() {
        return weight;
    }

    public AdventureItem weight(Long weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public AdventureAttributes getAdventureAttributes() {
        return adventureAttributes;
    }

    public AdventureItem adventureAttributes(AdventureAttributes adventureAttributes) {
        this.adventureAttributes = adventureAttributes;
        return this;
    }

    public void setAdventureAttributes(AdventureAttributes adventureAttributes) {
        this.adventureAttributes = adventureAttributes;
    }

    public AdventureInventoryChar getAdventureInventoryChar() {
        return adventureInventoryChar;
    }

    public AdventureItem adventureInventoryChar(AdventureInventoryChar adventureInventoryChar) {
        this.adventureInventoryChar = adventureInventoryChar;
        return this;
    }

    public void setAdventureInventoryChar(AdventureInventoryChar adventureInventoryChar) {
        this.adventureInventoryChar = adventureInventoryChar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdventureItem)) {
            return false;
        }
        return id != null && id.equals(((AdventureItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdventureItem{" +
            "id=" + getId() +
            ", isEquipment='" + isIsEquipment() + "'" +
            ", equipmentSlot='" + getEquipmentSlot() + "'" +
            ", price=" + getPrice() +
            ", weight=" + getWeight() +
            "}";
    }
}
