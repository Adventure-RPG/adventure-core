package com.adventure.core.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.adventure.core.domain.AdventureScript} entity.
 */
public class AdventureScriptDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    private byte[] file;

    private String fileContentType;
    private String argumentsScript;


    private String adventureSkillId;

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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getArgumentsScript() {
        return argumentsScript;
    }

    public void setArgumentsScript(String argumentsScript) {
        this.argumentsScript = argumentsScript;
    }

    public String getAdventureSkillId() {
        return adventureSkillId;
    }

    public void setAdventureSkillId(String adventureSkillId) {
        this.adventureSkillId = adventureSkillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdventureScriptDTO adventureScriptDTO = (AdventureScriptDTO) o;
        if (adventureScriptDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adventureScriptDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdventureScriptDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", file='" + getFile() + "'" +
            ", argumentsScript='" + getArgumentsScript() + "'" +
            ", adventureSkillId=" + getAdventureSkillId() +
            "}";
    }
}
