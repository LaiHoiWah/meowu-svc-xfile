package com.meowu.svc.babylon.commons.converter.mapstruct;

import com.meowu.svc.babylon.commons.entity.vo.ResourceVo;
import com.meowu.svc.babylon.commons.utils.UnitUtils;
import com.meowu.svc.babylon.core.archive.entity.Resource;

import java.util.Objects;

public class ResourceMapper{

    private ResourceMapper(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static ResourceVo toVo(Resource resource){
        if(Objects.nonNull(resource)){
            // document & unknown & audio & video & picture
            ResourceVo vo = new ResourceVo();
            vo.setId(resource.getId());
            vo.setDigest(resource.getDigest());
            vo.setExtension(resource.getExtension());
            vo.setSize(UnitUtils.sizeFormat(resource.getSize(), 1, false));

            // video
            vo.setVideoCodecName(resource.getVideoCodecName());
            if(Objects.nonNull(resource.getVideoBitRate())){
                vo.setVideoBitRate(UnitUtils.bitRateFormat(resource.getVideoBitRate()));
            }
            if(Objects.nonNull(resource.getVideoFps())){
                vo.setVideoFps(UnitUtils.fpsFormat(resource.getVideoFps()));
            }

            // audio & video
            vo.setAudioChannels(resource.getAudioChannels());
            vo.setAudioCodecName(resource.getAudioCodecName());
            if(Objects.nonNull(resource.getAudioBitRate())){
                vo.setAudioBitRate(UnitUtils.bitRateFormat(resource.getAudioBitRate()));
            }
            if(Objects.nonNull(resource.getDuration())){
                vo.setDuration(UnitUtils.durationFormat(resource.getDuration()));
            }
            // picture & video
            vo.setWidth(resource.getWidth());
            vo.setHeight(resource.getHeight());
            vo.setStatus(resource.getStatus());

            return vo;
        }

        return null;
    }
}
