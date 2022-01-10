package com.test.task.event.domain.infrastracture;

import com.test.task.event.domain.EventLog;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface EventLogRepository extends Repository<EventLog, String> {

    Optional<EventLog> findByEventLogId(String id);

    @Lock(LockModeType.OPTIMISTIC)
    void save(EventLog entity);
}
