package com.meowu.svc.babylon.commons.entity.vo;

import com.meowu.svc.babylon.commons.constants.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileInfoVo{

    private Long id;
    private Long archiveId;
    private Long resourceId;
    private ResourceType type;
    private String displayName;
    private Integer sequence;
    private Date createTime;
    private Date updateTime;

    // resources
    private ResourceVo resource;
}
