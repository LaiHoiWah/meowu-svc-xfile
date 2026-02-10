package com.meowu.svc.babylon.core.archive.controller;

import com.meowu.starter.web.commons.security.response.Response;
import com.meowu.svc.babylon.commons.entity.dto.ArchiveDetailCreateDto;
import com.meowu.svc.babylon.commons.entity.vo.ArchiveDetailVo;
import com.meowu.svc.babylon.core.archive.service.ArchiveDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/archive/detail")
public class ArchiveDetailController{

    @Autowired
    private ArchiveDetailService archiveDetailService;

    @PostMapping(
        value = "",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ArchiveDetailVo> save(@RequestBody ArchiveDetailCreateDto request){
        return new Response<ArchiveDetailVo>(archiveDetailService.save(request));
    }
}
