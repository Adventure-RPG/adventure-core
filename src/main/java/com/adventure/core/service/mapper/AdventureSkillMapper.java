package com.adventure.core.service.mapper;

import com.adventure.core.domain.*;
import com.adventure.core.service.dto.AdventureSkillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdventureSkill} and its DTO {@link AdventureSkillDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdventureSkillMapper extends EntityMapper<AdventureSkillDTO, AdventureSkill> {


    @Mapping(target = "adventureScripts", ignore = true)
    @Mapping(target = "removeAdventureScript", ignore = true)
    @Mapping(target = "adventureAccountCharacters", ignore = true)
    @Mapping(target = "removeAdventureAccountCharacter", ignore = true)
    AdventureSkill toEntity(AdventureSkillDTO adventureSkillDTO);

    default AdventureSkill fromId(String id) {
        if (id == null) {
            return null;
        }
        AdventureSkill adventureSkill = new AdventureSkill();
        adventureSkill.setId(id);
        return adventureSkill;
    }
}
