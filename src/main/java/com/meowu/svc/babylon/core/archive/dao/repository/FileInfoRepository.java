package com.meowu.svc.babylon.core.archive.dao.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.meowu.svc.babylon.core.archive.entity.FileInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends EntityGraphJpaRepository<FileInfo, Long>, EntityGraphJpaSpecificationExecutor<FileInfo>{

}
