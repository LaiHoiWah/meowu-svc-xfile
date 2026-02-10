package com.meowu.svc.babylon.core.archive.service;

import com.meowu.svc.babylon.commons.entity.dto.ArchiveCreateDto;
import com.meowu.svc.babylon.commons.entity.vo.ArchiveVo;

public interface ArchiveService{

    ArchiveVo save(ArchiveCreateDto request);

    ArchiveVo getById(Long id);
}
