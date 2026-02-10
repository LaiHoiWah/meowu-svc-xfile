package com.meowu.svc.babylon.core.archive.dao;

import com.meowu.starter.common.commons.utils.AssertUtils;
import com.meowu.starter.domain.commons.enums.RecordStatus;
import com.meowu.starter.jpa.commons.criteria.Criteria;
import com.meowu.starter.jpa.commons.criteria.Restrictions;
import com.meowu.starter.web.commons.security.stereotype.Dao;
import com.meowu.svc.babylon.core.archive.dao.repository.ArchiveRepository;
import com.meowu.svc.babylon.core.archive.entity.Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Dao
public class ArchiveDao{

    @Autowired
    private ArchiveRepository repository;

    public Archive save(Long archiveNo, String title, String description, RecordStatus status){
        AssertUtils.isNotNull(archiveNo, "Archive: ARCHIVE_NO must not be null");
        AssertUtils.isNotBlank(title, "Archive: TITLE must not be null");
        AssertUtils.isNotNull(status, "Archive: STATUS must not be null");

        // create record
        Archive archive = new Archive();
        archive.setArchiveNo(archiveNo);
        archive.setTitle(title);
        archive.setDescription(description);
        archive.setStatus(status);

        return repository.save(archive);
    }

    public Archive getById(Long id){
        AssertUtils.isNotNull(id, "Archive: ID must not be null");

        // criteria
        Criteria<Archive> criteria = new Criteria<Archive>();
        criteria.where(Restrictions.equal(Archive.Fields.id, id));

        return repository.findOne(criteria.getRestriction()).orElse(null);
    }
}
