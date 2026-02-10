package com.meowu.svc.babylon.core.archive.entity;

import com.meowu.starter.domain.commons.entity.Creatable;
import com.meowu.starter.domain.commons.entity.Identity;
import com.meowu.starter.domain.commons.entity.Sortable;
import com.meowu.starter.domain.commons.entity.Updatable;
import com.meowu.svc.babylon.commons.constants.enums.ResourceType;
import com.meowu.svc.babylon.commons.converter.jpa.ResourceTypeConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
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
@Table(name = "arch_file")
public class FileInfo extends Identity<Long> implements Creatable, Updatable, Sortable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int")
    private Long id;

    @Column(name = "archive_id", nullable = false, columnDefinition = "int")
    private Long archiveId;

    @Column(name = "resource_id", columnDefinition = "int")
    private Long resourceId;

    @Convert(converter = ResourceTypeConverter.class)
    @Column(name = "resource_type", columnDefinition = "smallint")
    private ResourceType type;

    @Column(name = "display_name", nullable = false, columnDefinition = "varchar(100)")
    private String displayName;

    @Column(name = "sequence", columnDefinition = "int")
    private Integer sequence;

    @CreatedDate
    @Column(name = "create_time", nullable = false, columnDefinition = "timestamp")
    private Date createTime;

    @Version
    @LastModifiedDate
    @Column(name = "update_time", columnDefinition = "timestamp")
    private Date updateTime;
}
