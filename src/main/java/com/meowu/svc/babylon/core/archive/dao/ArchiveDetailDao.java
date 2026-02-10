package com.meowu.svc.babylon.core.archive.dao;

import com.meowu.starter.common.commons.utils.AssertUtils;
import com.meowu.starter.jpa.commons.criteria.Criteria;
import com.meowu.starter.jpa.commons.criteria.Restrictions;
import com.meowu.starter.web.commons.security.stereotype.Dao;
import com.meowu.svc.babylon.core.archive.dao.repository.ArchiveDetailRepository;
import com.meowu.svc.babylon.core.archive.entity.ArchiveDetail;
import org.springframework.beans.factory.annotation.Autowired;

@Dao
public class ArchiveDetailDao{

    @Autowired
    private ArchiveDetailRepository repository;

    public ArchiveDetail save(Long archiveId, String content){
        AssertUtils.isNotNull(archiveId, "ArchiveDetail: ARCHIVE_ID must not be null");

        // create record
        ArchiveDetail detail = new ArchiveDetail();
        detail.setArchiveId(archiveId);
        detail.setContent(content);

        return repository.save(detail);
    }

    public ArchiveDetail getByArchiveId(Long archiveId){
        AssertUtils.isNotNull(archiveId, "ArchiveDetail: ARCHIVE_ID must not be null");

        // criteria
        Criteria<ArchiveDetail> criteria = new Criteria<ArchiveDetail>();
        criteria.where(Restrictions.equal(ArchiveDetail.Fields.archiveId, archiveId));

        return repository.findOne(criteria.getRestriction()).orElse(null);
    }
}
