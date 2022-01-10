package com.test.task.event.domain.infrastracture;

import com.test.task.event.domain.EventLog;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryEventLogRepository implements EventLogRepository {

    private ConcurrentHashMap<String, EventLog> inMemoryStore = new ConcurrentHashMap<>();

    @Override
    public Optional<EventLog> findByEventLogId(String id) {
        return Optional.of(inMemoryStore.get(id));
    }

    @Override
    public void save(EventLog entity) {

        if (inMemoryStore.containsKey(entity.getEventLogId())) {
            throw new DataIntegrityViolationException("Already exists");
        } else {
            inMemoryStore.put(entity.getEventLogId(), entity);
        }
    }
}
