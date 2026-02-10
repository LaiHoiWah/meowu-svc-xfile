package com.meowu.svc.babylon.commons.constants.enums;

import com.meowu.starter.domain.commons.enums.IntEnum;
import com.meowu.starter.domain.commons.utils.IntEnumReflectUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ResourceType implements IntEnum<ResourceType>{

    OTHERS(0, "Others"),

    AUDIO(1, "Audio"),

    VIDEO(2, "Video"),

    PICTURE(3, "Picture"),

    DOCUMENT(4, "Document"),

    ;

    private final Integer code;
    private final String  description;

    public static ResourceType getByCode(Integer code){
        return IntEnumReflectUtils.getByCode(ResourceType.class, code);
    }
}
