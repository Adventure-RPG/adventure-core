package com.adventure.core.service.mapper;

import com.adventure.core.domain.*;
import com.adventure.core.service.dto.AdventureModelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdventureModel} and its DTO {@link AdventureModelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdventureModelMapper extends EntityMapper<AdventureModelDTO, AdventureModel> {


    @Mapping(target = "adventureCategoryTypes", ignore = true)
    @Mapping(target = "removeAdventureCategoryType", ignore = true)
    @Mapping(target = "adventureRaceOptions", ignore = true)
    @Mapping(target = "removeAdventureRaceOptions", ignore = true)
    AdventureModel toEntity(AdventureModelDTO adventureModelDTO);

    default AdventureModel fromId(String id) {
        if (id == null) {
            return null;
        }
        AdventureModel adventureModel = new AdventureModel();
        adventureModel.setId(id);
        return adventureModel;
    }
}
