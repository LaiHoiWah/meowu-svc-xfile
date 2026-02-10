package com.meowu.svc.babylon.core.archive.service;

import com.meowu.svc.babylon.commons.entity.dto.ArchiveDetailCreateDto;
import com.meowu.svc.babylon.commons.entity.vo.ArchiveDetailVo;

public interface ArchiveDetailService{

    ArchiveDetailVo save(ArchiveDetailCreateDto request);
}
