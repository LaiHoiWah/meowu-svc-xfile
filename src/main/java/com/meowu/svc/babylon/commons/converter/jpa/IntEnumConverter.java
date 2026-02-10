package com.meowu.svc.babylon.commons.converter.jpa;

import com.meowu.starter.domain.commons.enums.IntEnum;
import com.meowu.starter.domain.commons.utils.IntEnumReflectUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.core.GenericTypeResolver;

import java.util.Objects;

@Converter(autoApply = true)
public class IntEnumConverter<E extends Enum<E> & IntEnum<E>> implements AttributeConverter<E, Integer>{

    private final Class<E> typeOf;

    @SuppressWarnings("unchecked")
    public IntEnumConverter(){
        this.typeOf = (Class<E>) GenericTypeResolver.resolveTypeArgument(getClass(), IntEnumConverter.class);
    }

    public IntEnumConverter(Class<E> typeOf){
        this.typeOf = typeOf;
    }

    @Override
    public Integer convertToDatabaseColumn(E e){
        return Objects.nonNull(e) ? e.getCode() : null;
    }

    @Override
    public E convertToEntityAttribute(Integer i){
        return Objects.nonNull(i) ? IntEnumReflectUtils.getByCode(typeOf, i) : null;
    }
}
