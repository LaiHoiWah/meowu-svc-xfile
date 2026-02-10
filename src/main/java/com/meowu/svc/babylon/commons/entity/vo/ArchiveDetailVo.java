package com.meowu.svc.babylon.commons.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArchiveDetailVo{

    private Long id;
    private Long archiveId;
    private String content;
    private Date createTime;
    private Date updateTime;
}
