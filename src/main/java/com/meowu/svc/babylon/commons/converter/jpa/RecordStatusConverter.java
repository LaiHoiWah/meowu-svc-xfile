package com.meowu.svc.babylon.commons.converter.jpa;

import com.meowu.starter.domain.commons.enums.RecordStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RecordStatusConverter extends IntEnumConverter<RecordStatus>{

    public RecordStatusConverter(){
        super(RecordStatus.class);
    }
}
