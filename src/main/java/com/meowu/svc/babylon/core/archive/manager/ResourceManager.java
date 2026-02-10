package com.meowu.svc.babylon.core.archive.manager;

import com.meowu.starter.common.commons.utils.AssertUtils;
import com.meowu.starter.domain.commons.enums.RecordStatus;
import com.meowu.starter.web.commons.security.stereotype.Manager;
import com.meowu.svc.babylon.commons.utils.RandomAccessUtils;
import com.meowu.svc.babylon.commons.utils.RangeUtils;
import com.meowu.svc.babylon.core.archive.dao.ResourceDao;
import com.meowu.svc.babylon.core.archive.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Manager
public class ResourceManager{

    @Autowired
    private ResourceDao resourceDao;

    public Resource save(String path, String digest, String extension, Long size,
                         Integer videoBitRate, Integer videoFps, String videoCodecName,
                         Integer audioBitRate, Integer audioChannels, String audioCodecName,
                         Long duration, Integer width, Integer height, RecordStatus status
    ){
        return resourceDao.save(
            path, digest, extension, size,
            videoBitRate, videoFps, videoCodecName,
            audioBitRate, audioChannels, audioCodecName,
            duration, width, height, status
        );
    }

    public Resource getById(Long id){
        return resourceDao.getById(id);
    }

    public Resource getByDigest(String digest){
        return resourceDao.getByDigest(digest);
    }

    public List<Resource> findByIds(List<Long> ids){
        return resourceDao.findByIds(ids);
    }

    public RandomAccessUtils.Metadata download(Long resourceId, RangeUtils.Range range){
        AssertUtils.isNotNull(resourceId, "Resource: RESOURCE_ID must not be null");

        String path = "C:\\Users\\Lihaihua\\Desktop\\video1869717699.mp4";
        return RandomAccessUtils.read(path, range.getStartPoint(), range.getEndPoint());
    }
}
