package com.adventure.core.service.mapper;

import com.adventure.core.domain.*;
import com.adventure.core.service.dto.AdventureAccountCharacterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdventureAccountCharacter} and its DTO {@link AdventureAccountCharacterDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdventureInventoryCharMapper.class, AdventureCharacteristicMapper.class, AdventureSkillMapper.class})
public interface AdventureAccountCharacterMapper extends EntityMapper<AdventureAccountCharacterDTO, AdventureAccountCharacter> {

    @Mapping(source = "adventureInventoryChar.id", target = "adventureInventoryCharId")
    @Mapping(source = "adventureCharacteristic.id", target = "adventureCharacteristicId")
    AdventureAccountCharacterDTO toDto(AdventureAccountCharacter adventureAccountCharacter);

    @Mapping(source = "adventureInventoryCharId", target = "adventureInventoryChar")
    @Mapping(source = "adventureCharacteristicId", target = "adventureCharacteristic")
    @Mapping(target = "adventureRaces", ignore = true)
    @Mapping(target = "removeAdventureRace", ignore = true)
    @Mapping(target = "removeAdventureSkill", ignore = true)
    AdventureAccountCharacter toEntity(AdventureAccountCharacterDTO adventureAccountCharacterDTO);

    default AdventureAccountCharacter fromId(String id) {
        if (id == null) {
            return null;
        }
        AdventureAccountCharacter adventureAccountCharacter = new AdventureAccountCharacter();
        adventureAccountCharacter.setId(id);
        return adventureAccountCharacter;
    }
}
