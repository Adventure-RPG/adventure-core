package com.adventure.core.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.adventure.core.domain.AdventureRace} entity.
 */
public class AdventureRaceDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String desc;

    
    private byte[] image;

    private String imageContentType;

    private Set<AdventureFractionDTO> adventureFractions = new HashSet<>();

    private String adventureAccountCharacterId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Set<AdventureFractionDTO> getAdventureFractions() {
        return adventureFractions;
    }

    public void setAdventureFractions(Set<AdventureFractionDTO> adventureFractions) {
        this.adventureFractions = adventureFractions;
    }

    public String getAdventureAccountCharacterId() {
        return adventureAccountCharacterId;
    }

    public void setAdventureAccountCharacterId(String adventureAccountCharacterId) {
        this.adventureAccountCharacterId = adventureAccountCharacterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdventureRaceDTO adventureRaceDTO = (AdventureRaceDTO) o;
        if (adventureRaceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adventureRaceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdventureRaceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            ", image='" + getImage() + "'" +
            ", adventureAccountCharacterId=" + getAdventureAccountCharacterId() +
            "}";
    }
}
