package com.meowu.svc.babylon.commons.converter.jpa;

import com.meowu.svc.babylon.commons.constants.enums.ResourceType;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ResourceTypeConverter extends IntEnumConverter<ResourceType>{

    public ResourceTypeConverter(){
        super(ResourceType.class);
    }
}
