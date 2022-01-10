package com.test.task.event.json;

import com.test.task.event.dto.EventLogDto;
import lombok.Data;

@Data
class EventLogJson {
    private String id;
    private String state;
    private String type;
    private String host;
    private Long timestamp;

    EventLogDto dto() {
        return EventLogDto.builder()
                .id(id)
                .state(state)
                .type(type)
                .host(host)
                .timestamp(timestamp)
                .build();
    }
}
