package com.meowu.svc.babylon.core.archive.service.impl;

import com.meowu.svc.babylon.core.archive.service.FileInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class FileInfoServiceImpl implements FileInfoService{
}
