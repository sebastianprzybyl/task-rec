package com.test.task.event.domain;

import com.test.task.event.dto.EventLogDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Data
@Entity
@EqualsAndHashCode
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String eventLogId;
    private Long duration;
    private String type;
    private String host;
    private Boolean alert;
    @Version
    Long version;

    EventLogDto dto() {
        return EventLogDto.builder()
                .id(eventLogId)
                .host(host)
                .timestamp(duration)
                .type(type)
                .build();
    }
}

