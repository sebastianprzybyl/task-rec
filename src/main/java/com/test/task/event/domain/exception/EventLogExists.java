package com.test.task.event.domain.exception;

import com.test.task.event.dto.EventLogDto;
import lombok.Getter;

public class EventLogExists extends RuntimeException {
    @Getter
    private EventLogDto eventLogDto;

    public EventLogExists(EventLogDto eventLogDto) {
        super("Event already exists", null, false, false);
        this.eventLogDto = eventLogDto;
    }
}