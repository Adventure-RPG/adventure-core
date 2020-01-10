package com.adventure.core.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.adventure.core.domain.AdventureAccountCharacter} entity.
 */
public class AdventureAccountCharacterDTO implements Serializable {

    private String id;

    @NotNull
    private String nickname;

    @NotNull
    private Boolean gender;


    private String adventureInventoryCharId;

    private String adventureCharacteristicId;

    private Set<AdventureSkillDTO> adventureSkills = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean isGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAdventureInventoryCharId() {
        return adventureInventoryCharId;
    }

    public void setAdventureInventoryCharId(String adventureInventoryCharId) {
        this.adventureInventoryCharId = adventureInventoryCharId;
    }

    public String getAdventureCharacteristicId() {
        return adventureCharacteristicId;
    }

    public void setAdventureCharacteristicId(String adventureCharacteristicId) {
        this.adventureCharacteristicId = adventureCharacteristicId;
    }

    public Set<AdventureSkillDTO> getAdventureSkills() {
        return adventureSkills;
    }

    public void setAdventureSkills(Set<AdventureSkillDTO> adventureSkills) {
        this.adventureSkills = adventureSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdventureAccountCharacterDTO adventureAccountCharacterDTO = (AdventureAccountCharacterDTO) o;
        if (adventureAccountCharacterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adventureAccountCharacterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdventureAccountCharacterDTO{" +
            "id=" + getId() +
            ", nickname='" + getNickname() + "'" +
            ", gender='" + isGender() + "'" +
            ", adventureInventoryCharId=" + getAdventureInventoryCharId() +
            ", adventureCharacteristicId=" + getAdventureCharacteristicId() +
            "}";
    }
}
