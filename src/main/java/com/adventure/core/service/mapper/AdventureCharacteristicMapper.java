package com.adventure.core.service.mapper;

import com.adventure.core.domain.*;
import com.adventure.core.service.dto.AdventureCharacteristicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdventureCharacteristic} and its DTO {@link AdventureCharacteristicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdventureCharacteristicMapper extends EntityMapper<AdventureCharacteristicDTO, AdventureCharacteristic> {



    default AdventureCharacteristic fromId(String id) {
        if (id == null) {
            return null;
        }
        AdventureCharacteristic adventureCharacteristic = new AdventureCharacteristic();
        adventureCharacteristic.setId(id);
        return adventureCharacteristic;
    }
}
