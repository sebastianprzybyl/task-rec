package com.test.task.event.domain;

import com.test.task.event.domain.infrastracture.EventLogRepository;
import com.test.task.event.domain.infrastracture.InMemoryEventLogRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventLogConfiguration {

    public EventLogFacade eventLogFacade() {
        return eventLogFacade(new InMemoryEventLogRepository());
    }

    @Bean
    public EventLogFacade eventLogFacade(EventLogRepository eventLogRepository) {
        EventLogSaver eventLogSaver = new EventLogSaver(eventLogRepository);
        EventLogFinder eventLogFinder = new EventLogFinder(eventLogRepository);
        EventLogCreator eventLogCreator = new EventLogCreator();
        return new EventLogFacade(eventLogSaver, eventLogFinder, eventLogCreator);
    }
}
