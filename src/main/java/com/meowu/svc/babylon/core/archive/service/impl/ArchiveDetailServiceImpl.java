package com.meowu.svc.babylon.core.archive.service.impl;

import com.meowu.svc.babylon.commons.converter.mapstruct.ArchiveDetailMapper;
import com.meowu.svc.babylon.commons.entity.dto.ArchiveDetailCreateDto;
import com.meowu.svc.babylon.commons.entity.vo.ArchiveDetailVo;
import com.meowu.svc.babylon.core.archive.entity.ArchiveDetail;
import com.meowu.svc.babylon.core.archive.manager.ArchiveDetailManager;
import com.meowu.svc.babylon.core.archive.service.ArchiveDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ArchiveDetailServiceImpl implements ArchiveDetailService{

    @Autowired
    private ArchiveDetailManager archiveDetailManager;

    @Transactional
    public ArchiveDetailVo save(ArchiveDetailCreateDto request){
        ArchiveDetail detail = archiveDetailManager.save(request.getArchiveId(), request.getContent());
        return ArchiveDetailMapper.toVo(detail);
    }
}
