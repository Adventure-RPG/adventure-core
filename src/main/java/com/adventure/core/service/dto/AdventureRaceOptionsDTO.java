package com.adventure.core.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.adventure.core.domain.AdventureRaceOptions} entity.
 */
public class AdventureRaceOptionsDTO implements Serializable {

    private String id;

    @NotNull
    @Min(value = 1)
    private Integer height;

    @NotNull
    @Min(value = 1)
    private Integer weight;


    private Set<AdventureModelDTO> adventureModels = new HashSet<>();

    private String adventureRaceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Set<AdventureModelDTO> getAdventureModels() {
        return adventureModels;
    }

    public void setAdventureModels(Set<AdventureModelDTO> adventureModels) {
        this.adventureModels = adventureModels;
    }

    public String getAdventureRaceId() {
        return adventureRaceId;
    }

    public void setAdventureRaceId(String adventureRaceId) {
        this.adventureRaceId = adventureRaceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdventureRaceOptionsDTO adventureRaceOptionsDTO = (AdventureRaceOptionsDTO) o;
        if (adventureRaceOptionsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adventureRaceOptionsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdventureRaceOptionsDTO{" +
            "id=" + getId() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", adventureRaceId=" + getAdventureRaceId() +
            "}";
    }
}
