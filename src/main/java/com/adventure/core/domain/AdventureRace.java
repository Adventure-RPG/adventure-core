package com.adventure.core.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * A AdventureRace.
 */
@Document(collection = "adventure_race")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "adventurerace")
public class AdventureRace implements Serializable {

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
    @Field("adventureRaceOptions")
    private Set<AdventureRaceOptions> adventureRaceOptions = new HashSet<>();

    @DBRef
    @Field("adventureFractions")
    private Set<AdventureFraction> adventureFractions = new HashSet<>();

    @DBRef
    @Field("adventureAccountCharacter")
    @JsonIgnoreProperties("adventureRaces")
    private AdventureAccountCharacter adventureAccountCharacter;

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

    public AdventureRace name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public AdventureRace desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getImage() {
        return image;
    }

    public AdventureRace image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public AdventureRace imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Set<AdventureRaceOptions> getAdventureRaceOptions() {
        return adventureRaceOptions;
    }

    public AdventureRace adventureRaceOptions(Set<AdventureRaceOptions> adventureRaceOptions) {
        this.adventureRaceOptions = adventureRaceOptions;
        return this;
    }

    public AdventureRace addAdventureRaceOptions(AdventureRaceOptions adventureRaceOptions) {
        this.adventureRaceOptions.add(adventureRaceOptions);
        adventureRaceOptions.setAdventureRace(this);
        return this;
    }

    public AdventureRace removeAdventureRaceOptions(AdventureRaceOptions adventureRaceOptions) {
        this.adventureRaceOptions.remove(adventureRaceOptions);
        adventureRaceOptions.setAdventureRace(null);
        return this;
    }

    public void setAdventureRaceOptions(Set<AdventureRaceOptions> adventureRaceOptions) {
        this.adventureRaceOptions = adventureRaceOptions;
    }

    public Set<AdventureFraction> getAdventureFractions() {
        return adventureFractions;
    }

    public AdventureRace adventureFractions(Set<AdventureFraction> adventureFractions) {
        this.adventureFractions = adventureFractions;
        return this;
    }

    public AdventureRace addAdventureFraction(AdventureFraction adventureFraction) {
        this.adventureFractions.add(adventureFraction);
        adventureFraction.getAdventureRaces().add(this);
        return this;
    }

    public AdventureRace removeAdventureFraction(AdventureFraction adventureFraction) {
        this.adventureFractions.remove(adventureFraction);
        adventureFraction.getAdventureRaces().remove(this);
        return this;
    }

    public void setAdventureFractions(Set<AdventureFraction> adventureFractions) {
        this.adventureFractions = adventureFractions;
    }

    public AdventureAccountCharacter getAdventureAccountCharacter() {
        return adventureAccountCharacter;
    }

    public AdventureRace adventureAccountCharacter(AdventureAccountCharacter adventureAccountCharacter) {
        this.adventureAccountCharacter = adventureAccountCharacter;
        return this;
    }

    public void setAdventureAccountCharacter(AdventureAccountCharacter adventureAccountCharacter) {
        this.adventureAccountCharacter = adventureAccountCharacter;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdventureRace)) {
            return false;
        }
        return id != null && id.equals(((AdventureRace) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdventureRace{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
