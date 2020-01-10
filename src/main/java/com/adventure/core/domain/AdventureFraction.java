package com.adventure.core.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A AdventureFraction.
 */
@Document(collection = "adventure_fraction")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "adventurefraction")
public class AdventureFraction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("desc")
    private String desc;

    
    @Field("image")
    private byte[] image;

    @Field("image_content_type")
    private String imageContentType;

    @DBRef
    @Field("adventureRaces")
    @JsonIgnore
    private Set<AdventureRace> adventureRaces = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AdventureFraction name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public AdventureFraction desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getImage() {
        return image;
    }

    public AdventureFraction image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public AdventureFraction imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Set<AdventureRace> getAdventureRaces() {
        return adventureRaces;
    }

    public AdventureFraction adventureRaces(Set<AdventureRace> adventureRaces) {
        this.adventureRaces = adventureRaces;
        return this;
    }

    public AdventureFraction addAdventureRace(AdventureRace adventureRace) {
        this.adventureRaces.add(adventureRace);
        adventureRace.getAdventureFractions().add(this);
        return this;
    }

    public AdventureFraction removeAdventureRace(AdventureRace adventureRace) {
        this.adventureRaces.remove(adventureRace);
        adventureRace.getAdventureFractions().remove(this);
        return this;
    }

    public void setAdventureRaces(Set<AdventureRace> adventureRaces) {
        this.adventureRaces = adventureRaces;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdventureFraction)) {
            return false;
        }
        return id != null && id.equals(((AdventureFraction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdventureFraction{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
