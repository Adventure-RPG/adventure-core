package com.adventure.core.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A AdventureModelOptions.
 */
@Document(collection = "adventure_model_options")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "adventuremodeloptions")
public class AdventureModelOptions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @Field("color")
    private String color;

    @DBRef
    @Field("adventureRaceOptions")
    @JsonIgnoreProperties("adventureModelOptions")
    private AdventureRaceOptions adventureRaceOptions;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public AdventureModelOptions color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public AdventureRaceOptions getAdventureRaceOptions() {
        return adventureRaceOptions;
    }

    public AdventureModelOptions adventureRaceOptions(AdventureRaceOptions adventureRaceOptions) {
        this.adventureRaceOptions = adventureRaceOptions;
        return this;
    }

    public void setAdventureRaceOptions(AdventureRaceOptions adventureRaceOptions) {
        this.adventureRaceOptions = adventureRaceOptions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdventureModelOptions)) {
            return false;
        }
        return id != null && id.equals(((AdventureModelOptions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdventureModelOptions{" +
            "id=" + getId() +
            ", color='" + getColor() + "'" +
            "}";
    }
}
