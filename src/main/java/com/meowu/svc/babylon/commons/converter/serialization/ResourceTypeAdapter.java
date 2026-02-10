package com.meowu.svc.babylon.commons.converter.serialization;

import com.google.gson.*;
import com.meowu.starter.common.commons.serialization.adapter.TypeAdapter;
import com.meowu.svc.babylon.commons.constants.enums.ResourceType;

import java.lang.reflect.Type;
import java.util.Objects;

public class ResourceTypeAdapter implements TypeAdapter<ResourceType>{

    @Override
    public Type getType(){
        return ResourceType.class;
    }

    @Override
    public ResourceType deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException{
        return Objects.isNull(element) ? null : ResourceType.getByCode(element.getAsInt());

    }

    @Override
    public JsonElement serialize(ResourceType resourceType, Type type, JsonSerializationContext context){
        return Objects.isNull(resourceType) ? null : new JsonPrimitive(resourceType.getCode());
    }
}
