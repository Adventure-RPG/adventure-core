package com.adventure.core.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.adventure.core.domain.AdventureCategoryType} entity.
 */
public class AdventureCategoryTypeDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    private String desc;


    private String adventureModelId;

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

    public String getAdventureModelId() {
        return adventureModelId;
    }

    public void setAdventureModelId(String adventureModelId) {
        this.adventureModelId = adventureModelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdventureCategoryTypeDTO adventureCategoryTypeDTO = (AdventureCategoryTypeDTO) o;
        if (adventureCategoryTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adventureCategoryTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdventureCategoryTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            ", adventureModelId=" + getAdventureModelId() +
            "}";
    }
}
