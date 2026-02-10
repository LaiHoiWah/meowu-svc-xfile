package com.meowu.svc.babylon.commons.converter.mapstruct;

import com.meowu.svc.babylon.commons.entity.vo.ArchiveDetailVo;
import com.meowu.svc.babylon.core.archive.entity.ArchiveDetail;

import java.util.Objects;

public class ArchiveDetailMapper{

    private ArchiveDetailMapper(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static ArchiveDetailVo toVo(ArchiveDetail detail){
        if(Objects.nonNull(detail)){
            ArchiveDetailVo vo = new ArchiveDetailVo();
            vo.setArchiveId(detail.getArchiveId());
            vo.setContent(detail.getContent());
            vo.setCreateTime(detail.getCreateTime());
            vo.setUpdateTime(detail.getUpdateTime());
            return vo;
        }

        return null;
    }
}
