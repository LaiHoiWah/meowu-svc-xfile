package com.meowu.svc.babylon.commons.converter.mapstruct;

import com.meowu.svc.babylon.commons.entity.vo.FileInfoVo;
import com.meowu.svc.babylon.core.archive.entity.FileInfo;
import com.meowu.svc.babylon.core.archive.entity.Resource;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileInfoMapper{

    private FileInfoMapper(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static FileInfoVo toVo(FileInfo fileInfo){
        if(Objects.nonNull(fileInfo)){
            FileInfoVo vo = new FileInfoVo();
            vo.setId(fileInfo.getId());
            vo.setArchiveId(fileInfo.getArchiveId());
            vo.setResourceId(fileInfo.getResourceId());
            vo.setType(fileInfo.getType());
            vo.setDisplayName(fileInfo.getDisplayName());
            vo.setSequence(fileInfo.getSequence());
            vo.setCreateTime(fileInfo.getCreateTime());
            vo.setUpdateTime(fileInfo.getUpdateTime());
            return vo;
        }

        return null;
    }

    public static List<FileInfoVo> toVoList(List<FileInfo> fileInfos){
        if(CollectionUtils.isNotEmpty(fileInfos)){
            return fileInfos.stream()
                            .filter(Objects::nonNull)
                            .map(FileInfoMapper::toVo)
                            .toList();
        }

        return Collections.emptyList();
    }

    public static void update(List<FileInfoVo> vos, List<Resource> resources){
        if(CollectionUtils.isNotEmpty(vos)){
            if(CollectionUtils.isNotEmpty(resources)){
                Map<Long, Resource> toResourceMap =
                    resources.stream()
                             .filter(r -> Objects.nonNull(r) && Objects.nonNull(r.getId()))
                             .collect(Collectors.toMap(Resource::getId, Function. identity()));

                vos.forEach(f -> {
                    Resource resource = toResourceMap.get(f.getResourceId());
                    if(Objects.nonNull(resource)){
                        f.setResource(ResourceMapper.toVo(resource));
                    }
                });
            }
        }
    }
}
