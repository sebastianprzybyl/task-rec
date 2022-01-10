package com.test.task.event.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Builder
@Value
@EqualsAndHashCode
public class EventLogDto {
    String id;
    Long timestamp;
    String state;
    String type;
    String host;
}
