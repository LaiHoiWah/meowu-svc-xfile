package com.meowu.svc.babylon.commons.entity.vo;

import com.meowu.starter.domain.commons.enums.RecordStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceVo{

    // document & unknown & audio & video & picture
    private Long id;
    private String digest;
    private String extension;
    private String size;
    private RecordStatus status;

    // video
    private String videoBitRate;
    private String videoFps;
    private String videoCodecName;

    // audio & video
    private String audioBitRate;
    private Integer audioChannels;
    private String audioCodecName;
    private String duration;

    // picture & video
    private Integer width;
    private Integer height;
}
