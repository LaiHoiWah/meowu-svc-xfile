package com.meowu.svc.babylon.core.archive.manager;

import com.meowu.starter.web.commons.security.stereotype.Manager;
import com.meowu.svc.babylon.core.archive.dao.FileInfoDao;
import com.meowu.svc.babylon.core.archive.entity.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Manager
public class FileInfoManager{

    @Autowired
    private FileInfoDao fileInfoDao;

    public List<FileInfo> findByArchiveId(Long archiveId){
        return fileInfoDao.findByArchiveId(archiveId);
    }
}
