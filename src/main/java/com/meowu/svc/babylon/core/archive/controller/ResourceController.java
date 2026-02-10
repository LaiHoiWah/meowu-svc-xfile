package com.meowu.svc.babylon.core.archive.controller;

import com.meowu.starter.web.commons.security.response.Response;
import com.meowu.svc.babylon.commons.entity.vo.ResourceVo;
import com.meowu.svc.babylon.core.archive.service.ResourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/resource")
public class ResourceController{

    @Autowired
    private ResourceService resourceService;

    @PostMapping(
        value = "/upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ResourceVo> upload(@RequestParam("file") MultipartFile file){
        return new Response<ResourceVo>(resourceService.upload(file));
    }

    @GetMapping(
        value = "/download/{id}",
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public void download(HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable("id") Long id
    ){
        resourceService.download(request, response, id);
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ResourceVo> getById(@PathVariable("id") Long id){
        return new Response<ResourceVo>(resourceService.getById(id));
    }

    @GetMapping(
        value = "/digest/{digest}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ResourceVo> getByDigest(@PathVariable("digest") String digest){
        return new Response<ResourceVo>(resourceService.getByDigest(digest));
    }
}
