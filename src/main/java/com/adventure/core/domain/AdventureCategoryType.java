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
 * A AdventureCategoryType.
 */
@Document(collection = "adventure_category_type")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "adventurecategorytype")
public class AdventureCategoryType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("desc")
    private String desc;

    @DBRef
    @Field("adventureModel")
    @JsonIgnoreProperties("adventureCategoryTypes")
    private AdventureModel adventureModel;

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

    public AdventureCategoryType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public AdventureCategoryType desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public AdventureModel getAdventureModel() {
        return adventureModel;
    }

    public AdventureCategoryType adventureModel(AdventureModel adventureModel) {
        this.adventureModel = adventureModel;
        return this;
    }

    public void setAdventureModel(AdventureModel adventureModel) {
        this.adventureModel = adventureModel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdventureCategoryType)) {
            return false;
        }
        return id != null && id.equals(((AdventureCategoryType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdventureCategoryType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
