package com.test.task.event.domain;

import com.test.task.event.dto.EventLogDto;

import static java.util.Objects.requireNonNull;

class EventLogCreator {

    EventLog from(EventLogDto eventLogDto) {
        EventLog eventLog = new EventLog();
        eventLog.setDuration(eventLogDto.getTimestamp());
        eventLog.setEventLogId(eventLogDto.getId());
        eventLog.setType(eventLogDto.getType());
        eventLog.setHost(eventLogDto.getHost());
        eventLog.setAlert(false);

        return eventLog;
    }

    EventLog merge(EventLog eventLogDb, EventLog eventLog) {
        requireNonNull(eventLog);
        requireNonNull(eventLogDb);
        //TODO fix process od merging data

        eventLogDb.setDuration(new Duration(eventLogDb.getDuration(), eventLog.getDuration()).getValue());

        eventLogDb.setAlert(new Alert(eventLogDb.getDuration()).getValue());

        eventLogDb.setType(eventLogDb.getType() != null ? eventLogDb.getType() : eventLog.getType());
        eventLogDb.setHost(eventLogDb.getHost() != null ? eventLogDb.getHost() : eventLog.getHost());

        return eventLogDb;
    }
}
