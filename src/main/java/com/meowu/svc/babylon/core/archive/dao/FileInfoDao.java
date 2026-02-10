package com.meowu.svc.babylon.core.archive.dao;

import com.meowu.starter.common.commons.utils.AssertUtils;
import com.meowu.starter.jpa.commons.criteria.Criteria;
import com.meowu.starter.jpa.commons.criteria.Restrictions;
import com.meowu.starter.web.commons.security.stereotype.Dao;
import com.meowu.svc.babylon.core.archive.dao.repository.FileInfoRepository;
import com.meowu.svc.babylon.core.archive.entity.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Dao
public class FileInfoDao{

    @Autowired
    private FileInfoRepository repository;

    public FileInfo save(Long archiveId, String displayName, Integer sequence){
        AssertUtils.isNotNull(archiveId, "FileInfo: ARCHIVE_ID must not be null");
        AssertUtils.isNotBlank(displayName, "FileInfo: DISPLAY_NAME must not be null");

        // create record
        FileInfo fileInfo = new FileInfo();
        fileInfo.setArchiveId(archiveId);
        fileInfo.setDisplayName(displayName);
        fileInfo.setSequence(sequence);

        return fileInfo;
    }

    public List<FileInfo> findByArchiveId(Long archiveId){
        AssertUtils.isNotNull(archiveId, "FileInfo: ARCHIVE_ID must not be null");

        // criteria
        Criteria<FileInfo> criteria = new Criteria<FileInfo>();
        criteria.where(Restrictions.equal(FileInfo.Fields.archiveId, archiveId));
        criteria.orderBy(Restrictions.asc(FileInfo.Fields.sequence));

        return repository.findAll(criteria.getRestriction());
    }
}
