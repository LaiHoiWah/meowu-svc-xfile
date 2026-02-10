package com.meowu.svc.babylon.core.archive.entity;

import com.meowu.starter.domain.commons.entity.Creatable;
import com.meowu.starter.domain.commons.entity.Deletable;
import com.meowu.starter.domain.commons.entity.Identity;
import com.meowu.starter.domain.commons.entity.Updatable;
import com.meowu.starter.domain.commons.enums.RecordStatus;
import com.meowu.svc.babylon.commons.converter.jpa.RecordStatusConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "archive")
public class Archive extends Identity<Long> implements Creatable, Updatable, Deletable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int")
    private Long id;

    @Column(name = "archive_no", unique = true, nullable = false, columnDefinition = "bigint")
    private Long archiveNo;

    @CreatedBy
    @Column(name = "account_id", nullable = false, columnDefinition = "int")
    private Long accountId;

    @Column(name = "title", columnDefinition = "varchar(50)")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Convert(converter = RecordStatusConverter.class)
    @Column(name = "status", nullable = false, columnDefinition = "smallint")
    private RecordStatus status;

    @CreatedDate
    @Column(name = "create_time", nullable = false, columnDefinition = "timestamp")
    private Date createTime;

    @Version
    @LastModifiedDate
    @Column(name = "update_time", columnDefinition = "timestamp")
    private Date updateTime;

    @Column(name = "delete_time", columnDefinition = "timestamp")
    private Date deleteTime;
}
