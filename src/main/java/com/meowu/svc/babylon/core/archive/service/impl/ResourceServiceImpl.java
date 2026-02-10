package com.meowu.svc.babylon.core.archive.service.impl;

import com.meowu.starter.common.commons.utils.AssertUtils;
import com.meowu.starter.common.commons.utils.DigestUtils;
import com.meowu.starter.common.commons.utils.SnowflakeUtils;
import com.meowu.starter.domain.commons.enums.RecordStatus;
import com.meowu.svc.babylon.commons.configuration.properties.ResourceProperties;
import com.meowu.svc.babylon.commons.constants.enums.ResourceType;
import com.meowu.svc.babylon.commons.converter.mapstruct.ResourceMapper;
import com.meowu.svc.babylon.commons.entity.vo.ResourceVo;
import com.meowu.svc.babylon.commons.security.exception.NotFoundException;
import com.meowu.svc.babylon.commons.security.exception.ResourceUploadException;
import com.meowu.svc.babylon.commons.utils.*;
import com.meowu.svc.babylon.core.archive.entity.Resource;
import com.meowu.svc.babylon.core.archive.manager.ResourceManager;
import com.meowu.svc.babylon.core.archive.service.ResourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

@Transactional(readOnly = true)
@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceProperties resourceProperties;

    @Autowired
    private SnowflakeUtils filenameSnowflakeUtils;

    @Autowired
    private ResourceManager resourceManager;

    @Transactional
    @Override
    public ResourceVo upload(MultipartFile file){
        try{
            String path      = StringUtils.EMPTY;
            String filename  = String.valueOf(filenameSnowflakeUtils.nextId());
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String digest    = DigestUtils.digestToHexString(file.getInputStream(), DigestUtils.ALGORITHM_SHA3_512);
            Long   size      = file.getSize();

            // get by digest
            Resource resource = resourceManager.getByDigest(digest);
            // not exist
            if(Objects.isNull(resource)){
                ResourceType resourceType = ResourceInfoUtils.getMediaType(extension);
                switch(resourceType){
                    case DOCUMENT -> path = resourceProperties.getDocumentDir();
                    case VIDEO -> path = resourceProperties.getVideoDir();
                    case AUDIO -> path = resourceProperties.getAudioDir();
                    case PICTURE -> path = resourceProperties.getPictureDir();
                    case OTHERS -> path = resourceProperties.getOthersDir();
                }

                // transfer
                String dest = path + File.separator + filename + "." + extension;
                file.transferTo(Paths.get(dest));
                // get metadata
                ResourceInfoUtils.Metadata metadata = ResourceInfoUtils.getMetadata(dest);
                // save
                resource = resourceManager.save(
                    dest, digest, extension, size,
                    metadata.getVideoBitRate(), metadata.getVideoFps(), metadata.getVideoCodecName(),
                    metadata.getAudioBitRate(), metadata.getAudioChannels(), metadata.getAudioCodecName(),
                    metadata.getDuration(), metadata.getWidth(), metadata.getHeight(), RecordStatus.ACTIVE
                );
            }

            return ResourceMapper.toVo(resource);
        }catch(Exception e){
            throw new ResourceUploadException(e.getMessage(), e);
        }
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, Long id){
        AssertUtils.isNotNull(id, "Resource: ID must not be null");
        // get resource
        Resource resource = resourceManager.getById(id);
        // not found
        if(Objects.isNull(resource)){
            throw new NotFoundException("Resource: not found by ID[" + id + "]");
        }

        // parse range from header info
        RangeUtils.Range range = RangeUtils.parse(request);
        // get path
        String path = resource.getPath();
        // read binary
        RandomAccessUtils.Metadata metadata = RandomAccessUtils.read(path, range.getStartPoint(), range.getEndPoint());
        // write into response
        DownloadUtils.writeIntoResponse(response, metadata);
    }

    @Override
    public ResourceVo getById(Long id){
        Resource resource = resourceManager.getById(id);
        return ResourceMapper.toVo(resource);
    }

    @Override
    public ResourceVo getByDigest(String digest){
        Resource resource = resourceManager.getByDigest(digest);
        return ResourceMapper.toVo(resource);
    }
}
