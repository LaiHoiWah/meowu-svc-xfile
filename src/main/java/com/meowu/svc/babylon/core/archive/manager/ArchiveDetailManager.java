package com.meowu.svc.babylon.core.archive.manager;

import com.meowu.starter.web.commons.security.stereotype.Manager;
import com.meowu.svc.babylon.core.archive.dao.ArchiveDetailDao;
import com.meowu.svc.babylon.core.archive.entity.ArchiveDetail;
import org.springframework.beans.factory.annotation.Autowired;

@Manager
public class ArchiveDetailManager{

    @Autowired
    private ArchiveDetailDao archiveDetailDao;

    public ArchiveDetail save(Long archiveId, String content){
        return archiveDetailDao.save(archiveId, content);
    }

    public ArchiveDetail getByArchiveId(Long archiveId){
        return archiveDetailDao.getByArchiveId(archiveId);
    }
}
