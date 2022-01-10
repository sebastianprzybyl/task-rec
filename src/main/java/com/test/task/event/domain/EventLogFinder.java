package com.test.task.event.domain;

import com.test.task.event.domain.infrastracture.EventLogRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
class EventLogFinder {

    private final EventLogRepository eventRepository;

    Optional<EventLog> findEventLog(String eventLogId) {
        return eventRepository.findByEventLogId(eventLogId);

    }
}
