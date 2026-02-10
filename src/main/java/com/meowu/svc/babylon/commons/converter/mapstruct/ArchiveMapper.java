package com.meowu.svc.babylon.commons.converter.mapstruct;

import com.meowu.svc.babylon.commons.entity.vo.ArchiveVo;
import com.meowu.svc.babylon.commons.entity.vo.FileInfoVo;
import com.meowu.svc.babylon.core.archive.entity.Archive;
import com.meowu.svc.babylon.core.archive.entity.ArchiveDetail;
import com.meowu.svc.babylon.core.archive.entity.FileInfo;
import com.meowu.svc.babylon.core.archive.entity.Resource;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class ArchiveMapper{

    private ArchiveMapper(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static ArchiveVo toVo(Archive archive){
        if(Objects.nonNull(archive)){
            ArchiveVo vo = new ArchiveVo();
            vo.setId(archive.getId());
            vo.setArchiveNo(String.valueOf(archive.getArchiveNo()));
            vo.setTitle(archive.getTitle());
            vo.setDescription(archive.getDescription());
            vo.setStatus(archive.getStatus());
            vo.setCreateTime(archive.getCreateTime());
            vo.setUpdateTime(archive.getUpdateTime());
            return vo;
        }

        return null;
    }

    public static void update(ArchiveVo vo, ArchiveDetail detail, List<FileInfo> fileInofs, List<Resource> resources){
        if(Objects.nonNull(vo)){
            // detail
            if(Objects.nonNull(detail)){
                vo.setDetail(ArchiveDetailMapper.toVo(detail));
            }
            // file info
            if(CollectionUtils.isNotEmpty(fileInofs)){
                List<FileInfoVo> fileInfoVos = FileInfoMapper.toVoList(fileInofs);
                vo.setFileInfos(fileInfoVos);
                // resource
                if(CollectionUtils.isNotEmpty(fileInfoVos)){
                    FileInfoMapper.update(fileInfoVos, resources);
                }
            }
        }
    }
}
