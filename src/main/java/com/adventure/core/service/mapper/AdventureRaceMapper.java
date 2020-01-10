package com.adventure.core.service.mapper;

import com.adventure.core.domain.*;
import com.adventure.core.service.dto.AdventureRaceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdventureRace} and its DTO {@link AdventureRaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdventureFractionMapper.class, AdventureAccountCharacterMapper.class})
public interface AdventureRaceMapper extends EntityMapper<AdventureRaceDTO, AdventureRace> {

    @Mapping(source = "adventureAccountCharacter.id", target = "adventureAccountCharacterId")
    AdventureRaceDTO toDto(AdventureRace adventureRace);

    @Mapping(target = "adventureRaceOptions", ignore = true)
    @Mapping(target = "removeAdventureRaceOptions", ignore = true)
    @Mapping(target = "removeAdventureFraction", ignore = true)
    @Mapping(source = "adventureAccountCharacterId", target = "adventureAccountCharacter")
    AdventureRace toEntity(AdventureRaceDTO adventureRaceDTO);

    default AdventureRace fromId(String id) {
        if (id == null) {
            return null;
        }
        AdventureRace adventureRace = new AdventureRace();
        adventureRace.setId(id);
        return adventureRace;
    }
}
