package com.test.task.event.domain;

import com.test.task.event.dto.EventLogDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class EventLogFacade {

    private final EventLogSaver eventLogSaver;
    private final EventLogFinder eventLogFinder;
    private final EventLogCreator eventLogCreator;

    @Transactional
    public void saveEventLog(EventLogDto eventLogDto) {

        //TODO cast problems will be good to save in separate table to manually check
        EventLog eventLog = eventLogCreator.from(eventLogDto);

        eventLogSaver.save(eventLog);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateEventLog(EventLogDto eventLogDto) {
        //ToDo handle more then two records with the same Id
        log.debug("Update event log : " + eventLogDto.getId());
        EventLog eventLog = eventLogCreator.from(eventLogDto);

        Optional<EventLog> eventLogOptional = eventLogFinder.findEventLog(eventLog.getEventLogId());

        eventLogOptional
                .ifPresent(log -> eventLogSaver.save(
                        eventLogCreator.merge(log, eventLog)
                ));
    }
}
