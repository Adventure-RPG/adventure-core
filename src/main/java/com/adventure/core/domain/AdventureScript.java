package com.adventure.core.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A AdventureScript.
 */
@Document(collection = "adventure_script")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "adventurescript")
public class AdventureScript implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("file")
    private byte[] file;

    @Field("file_content_type")
    private String fileContentType;

    @Field("arguments_script")
    private String argumentsScript;

    @DBRef
    @Field("adventureSkill")
    @JsonIgnoreProperties("adventureScripts")
    private AdventureSkill adventureSkill;

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

    public AdventureScript name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public AdventureScript file(byte[] file) {
        this.file = file;
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public AdventureScript fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getArgumentsScript() {
        return argumentsScript;
    }

    public AdventureScript argumentsScript(String argumentsScript) {
        this.argumentsScript = argumentsScript;
        return this;
    }

    public void setArgumentsScript(String argumentsScript) {
        this.argumentsScript = argumentsScript;
    }

    public AdventureSkill getAdventureSkill() {
        return adventureSkill;
    }

    public AdventureScript adventureSkill(AdventureSkill adventureSkill) {
        this.adventureSkill = adventureSkill;
        return this;
    }

    public void setAdventureSkill(AdventureSkill adventureSkill) {
        this.adventureSkill = adventureSkill;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdventureScript)) {
            return false;
        }
        return id != null && id.equals(((AdventureScript) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdventureScript{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            ", argumentsScript='" + getArgumentsScript() + "'" +
            "}";
    }
}
