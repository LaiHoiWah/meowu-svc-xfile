package com.meowu.svc.babylon.core.archive.dao;

import com.meowu.starter.common.commons.utils.AssertUtils;
import com.meowu.starter.domain.commons.enums.RecordStatus;
import com.meowu.starter.jpa.commons.criteria.Criteria;
import com.meowu.starter.jpa.commons.criteria.Restrictions;
import com.meowu.starter.web.commons.security.stereotype.Dao;
import com.meowu.svc.babylon.core.archive.dao.repository.ResourceRepository;
import com.meowu.svc.babylon.core.archive.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Dao
public class ResourceDao{

    @Autowired
    private ResourceRepository repository;

    public Resource save(String path, String digest, String extension, Long size,
                         Integer videoBitRate, Integer videoFps, String videoCodecName,
                         Integer audioBitRate, Integer audioChannels, String audioCodecName,
                         Long duration, Integer width, Integer height, RecordStatus status
    ){
        AssertUtils.isNotBlank(path, "Resource: PATH must not be null");
        AssertUtils.isNotBlank(digest, "Resource: DIGEST must not be null");
        AssertUtils.isNotNull(size, "Resource: SIZE must not be null");
        AssertUtils.isNotNull(status, "Resource: STATUS must not be null");

        // create record
        Resource resource = new Resource();
        resource.setPath(path);
        resource.setDigest(digest);
        resource.setExtension(extension);
        resource.setSize(size);
        resource.setVideoBitRate(videoBitRate);
        resource.setVideoFps(videoFps);
        resource.setVideoCodecName(videoCodecName);
        resource.setAudioBitRate(audioBitRate);
        resource.setAudioChannels(audioChannels);
        resource.setAudioCodecName(audioCodecName);
        resource.setDuration(duration);
        resource.setWidth(width);
        resource.setHeight(height);
        resource.setStatus(status);

        return repository.save(resource);
    }

    public Resource getByDigest(String digest){
        AssertUtils.isNotBlank(digest, "Resource: DIGEST must not be null");

        // criteria
        Criteria<Resource> criteria = new Criteria<Resource>();
        criteria.where(Restrictions.equal(Resource.Fields.digest, digest));

        return repository.findOne(criteria.getRestriction()).orElse(null);
    }

    public Resource getById(Long id){
        AssertUtils.isNotNull(id, "Resource: ID must not be null");

        // criteria
        Criteria<Resource> criteria = new Criteria<Resource>();
        criteria.where(Restrictions.equal(Resource.Fields.id, id));

        return repository.findOne(criteria.getRestriction()).orElse(null);
    }

    public List<Resource> findByIds(List<Long> ids){
        AssertUtils.isNotEmpty(ids, "Resource: ID list must not be null");

        // criteria
        Criteria<Resource> criteria = new Criteria<Resource>();
        criteria.where(Restrictions.in(Resource.Fields.id, ids));

        return repository.findAll(criteria.getRestriction());
    }
}
