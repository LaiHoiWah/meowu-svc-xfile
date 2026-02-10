package com.meowu.svc.babylon.core.archive.manager;

import com.meowu.starter.common.commons.utils.SnowflakeUtils;
import com.meowu.starter.domain.commons.enums.RecordStatus;
import com.meowu.starter.web.commons.security.stereotype.Manager;
import com.meowu.svc.babylon.core.archive.dao.ArchiveDao;
import com.meowu.svc.babylon.core.archive.entity.Archive;
import org.springframework.beans.factory.annotation.Autowired;

@Manager
public class ArchiveManager{

    @Autowired
    private SnowflakeUtils archiveNoSnowflakeUtils;

    @Autowired
    private ArchiveDao archiveDao;

    public Archive save(String title, String description){
        // archive no
        Long archiveNo = archiveNoSnowflakeUtils.nextId();
        // create archive
        return archiveDao.save(archiveNo, title, description, RecordStatus.ACTIVE);
    }

    public Archive getById(Long id){
        return archiveDao.getById(id);
    }
}
