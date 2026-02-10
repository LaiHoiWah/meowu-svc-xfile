package com.meowu.svc.babylon.core.archive.service;

import com.meowu.svc.babylon.commons.entity.vo.ResourceVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ResourceService{

    ResourceVo upload(MultipartFile file);

    void download(HttpServletRequest request, HttpServletResponse response, Long id);

    ResourceVo getById(Long id);

    ResourceVo getByDigest(String digest);
}
