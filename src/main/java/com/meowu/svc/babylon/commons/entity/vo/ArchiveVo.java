package com.meowu.svc.babylon.commons.entity.vo;

import com.meowu.starter.domain.commons.enums.RecordStatus;
import com.meowu.svc.lilith.client.commons.entity.vo.UserInfoVo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ArchiveVo{

    private Long id;
    private String archiveNo;
    private String title;
    private String description;
    private RecordStatus status;
    private Date createTime;
    private Date updateTime;
    // details
    private ArchiveDetailVo detail;
    // creator
    private UserInfoVo creator;
    // resources
    private List<FileInfoVo> fileInfos;
}
