package com.test.task.event.domain;

import com.test.task.event.domain.exception.EventLogExists;
import com.test.task.event.domain.infrastracture.EventLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;

@Slf4j
@AllArgsConstructor
class EventLogSaver {

    private final EventLogRepository eventRepository;

    void save(EventLog eventLog) throws EventLogExists {
        log.info("Saving completed of event log " + eventLog.toString());
        try {
            eventRepository.save(eventLog);
        } catch (DataIntegrityViolationException e) {
            //TODO need to be sure that this is correct exception
            throw new EventLogExists(eventLog.dto());
        }
    }
}
