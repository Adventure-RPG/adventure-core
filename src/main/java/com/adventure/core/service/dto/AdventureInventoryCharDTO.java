package com.adventure.core.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.adventure.core.domain.AdventureInventoryChar} entity.
 */
public class AdventureInventoryCharDTO implements Serializable {

    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdventureInventoryCharDTO adventureInventoryCharDTO = (AdventureInventoryCharDTO) o;
        if (adventureInventoryCharDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adventureInventoryCharDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdventureInventoryCharDTO{" +
            "id=" + getId() +
            "}";
    }
}
