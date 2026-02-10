package com.meowu.svc.babylon.core.archive.entity;

import com.meowu.starter.domain.commons.entity.Creatable;
import com.meowu.starter.domain.commons.entity.Identity;
import com.meowu.starter.domain.commons.entity.Sortable;
import com.meowu.starter.domain.commons.entity.Updatable;
import com.meowu.starter.domain.commons.enums.RecordStatus;
import com.meowu.svc.babylon.commons.constants.enums.ResourceType;
import com.meowu.svc.babylon.commons.converter.jpa.RecordStatusConverter;
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
@Table(name = "arch_resource")
public class Resource extends Identity<Long> implements Creatable, Updatable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int")
    private Long id;

    @Column(name = "path", nullable = false, columnDefinition = "text")
    private String path;

    @Column(name = "digest", nullable = false, columnDefinition = "text")
    private String digest;

    @Column(name = "extension", columnDefinition = "varchar(4)")
    private String extension;

    @Column(name = "size", nullable = false, columnDefinition = "int")
    private Long size;

    //------------ video ------------//
    @Column(name = "video_bit_rate", columnDefinition = "int")
    private Integer videoBitRate;

    @Column(name = "video_fps", columnDefinition = "int")
    private Integer videoFps;

    @Column(name = "video_codec_name", columnDefinition = "varchar(10)")
    private String videoCodecName;

    //------------ audio & video ------------//
    @Column(name = "audio_bit_rate", columnDefinition = "int")
    private Integer audioBitRate;

    @Column(name = "audio_channels", columnDefinition = "smallint")
    private Integer audioChannels;

    @Column(name = "audio_codec_name", columnDefinition = "varchar(50)")
    private String audioCodecName;

    @Column(name = "duration", columnDefinition = "bigint")
    private Long duration;

    //------------ picture & video ------------//
    @Column(name = "width", columnDefinition = "int")
    private Integer width;

    @Column(name = "height", columnDefinition = "int")
    private Integer height;

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
}
