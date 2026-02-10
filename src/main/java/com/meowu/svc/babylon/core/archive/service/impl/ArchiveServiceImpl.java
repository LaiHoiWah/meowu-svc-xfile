package com.meowu.svc.babylon.core.archive.service.impl;

import com.google.common.collect.Lists;
import com.meowu.svc.babylon.commons.converter.mapstruct.ArchiveMapper;
import com.meowu.svc.babylon.commons.entity.dto.ArchiveCreateDto;
import com.meowu.svc.babylon.commons.entity.vo.ArchiveVo;
import com.meowu.svc.babylon.commons.security.exception.NotFoundException;
import com.meowu.svc.babylon.core.archive.entity.Archive;
import com.meowu.svc.babylon.core.archive.entity.ArchiveDetail;
import com.meowu.svc.babylon.core.archive.entity.FileInfo;
import com.meowu.svc.babylon.core.archive.entity.Resource;
import com.meowu.svc.babylon.core.archive.manager.ArchiveDetailManager;
import com.meowu.svc.babylon.core.archive.manager.ArchiveManager;
import com.meowu.svc.babylon.core.archive.manager.FileInfoManager;
import com.meowu.svc.babylon.core.archive.manager.ResourceManager;
import com.meowu.svc.babylon.core.archive.service.ArchiveService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Transactional(readOnly = true)
@Service
public class ArchiveServiceImpl implements ArchiveService{

    @Autowired
    private ArchiveManager archiveManager;

    @Autowired
    private ArchiveDetailManager archiveDetailManager;

    @Autowired
    private FileInfoManager fileInfoManager;

    @Autowired
    private ResourceManager resourceManager;

    @Transactional
    @Override
    public ArchiveVo save(ArchiveCreateDto request){
        Archive archive = archiveManager.save(request.getTitle(), request.getDescription());
        return ArchiveMapper.toVo(archive);
    }

    @Override
    public ArchiveVo getById(Long archiveId){
        // get archive
        Archive archive = archiveManager.getById(archiveId);
        if(Objects.nonNull(archive)){
            // get archive detail
            ArchiveDetail detail = archiveDetailManager.getByArchiveId(archiveId);
            // get file info
            List<FileInfo> fileInfos = fileInfoManager.findByArchiveId(archiveId);
            // get resources
            List<Resource> resources = Lists.newArrayList();
            List<Long> resourceIds =
                fileInfos.stream()
                         .filter(fileInfo -> Objects.nonNull(fileInfo) && Objects.nonNull(fileInfo.getResourceId()))
                         .sorted(Comparator.comparing(FileInfo::getSequence))
                         .map(FileInfo::getResourceId)
                         .toList();

            if(CollectionUtils.isNotEmpty(resourceIds)){
                resources = resourceManager.findByIds(resourceIds);
            }

            // to vo
            ArchiveVo vo = ArchiveMapper.toVo(archive);
            ArchiveMapper.update(vo, detail, fileInfos, resources);
            return vo;
        }

        throw new NotFoundException("Archive: Not found archive by id[" + archiveId + "]");
    }
}
