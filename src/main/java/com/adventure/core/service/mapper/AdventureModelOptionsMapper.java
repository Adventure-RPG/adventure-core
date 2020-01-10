package com.adventure.core.service.mapper;

import com.adventure.core.domain.*;
import com.adventure.core.service.dto.AdventureModelOptionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdventureModelOptions} and its DTO {@link AdventureModelOptionsDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdventureRaceOptionsMapper.class})
public interface AdventureModelOptionsMapper extends EntityMapper<AdventureModelOptionsDTO, AdventureModelOptions> {

    @Mapping(source = "adventureRaceOptions.id", target = "adventureRaceOptionsId")
    AdventureModelOptionsDTO toDto(AdventureModelOptions adventureModelOptions);

    @Mapping(source = "adventureRaceOptionsId", target = "adventureRaceOptions")
    AdventureModelOptions toEntity(AdventureModelOptionsDTO adventureModelOptionsDTO);

    default AdventureModelOptions fromId(String id) {
        if (id == null) {
            return null;
        }
        AdventureModelOptions adventureModelOptions = new AdventureModelOptions();
        adventureModelOptions.setId(id);
        return adventureModelOptions;
    }
}
