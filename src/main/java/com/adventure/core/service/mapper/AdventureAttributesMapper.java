package com.adventure.core.service.mapper;

import com.adventure.core.domain.*;
import com.adventure.core.service.dto.AdventureAttributesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdventureAttributes} and its DTO {@link AdventureAttributesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdventureAttributesMapper extends EntityMapper<AdventureAttributesDTO, AdventureAttributes> {



    default AdventureAttributes fromId(String id) {
        if (id == null) {
            return null;
        }
        AdventureAttributes adventureAttributes = new AdventureAttributes();
        adventureAttributes.setId(id);
        return adventureAttributes;
    }
}
