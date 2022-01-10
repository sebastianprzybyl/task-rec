package com.test.task.event.domain;

import com.test.task.event.domain.exception.EventLogExists;
import com.test.task.event.dto.EventLogDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EventLogAsyncFacade {

    private final EventLogFacade eventLogFacade;

    @Async
    public void handleEventLog(EventLogDto eventLogDto) {
        log.debug("Start async task for :" + eventLogDto.getId());
        try {
            eventLogFacade.saveEventLog(eventLogDto);
        } catch (EventLogExists e) {
            log.debug("EventLog already exist, trying to update");
            eventLogFacade.updateEventLog(e.getEventLogDto());
        }
    }
}
