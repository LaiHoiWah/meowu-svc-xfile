package com.meowu.svc.babylon.core.archive.controller;

import com.meowu.starter.web.commons.security.response.Response;
import com.meowu.svc.babylon.commons.entity.dto.ArchiveCreateDto;
import com.meowu.svc.babylon.commons.entity.vo.ArchiveVo;
import com.meowu.svc.babylon.core.archive.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/archive")
public class ArchiveController{

    @Autowired
    private ArchiveService archiveService;

    @PostMapping(
        value = "",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ArchiveVo> save(@RequestBody ArchiveCreateDto request){
        return new Response<ArchiveVo>(archiveService.save(request));
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ArchiveVo> getById(@PathVariable("id") Long id){
        return new Response<ArchiveVo>(archiveService.getById(id));
    }
}
