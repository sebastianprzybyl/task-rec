package com.test.task.event.json;

import com.test.task.event.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EventLogJsonStreamingConfiguration {

    @Bean
    EventLogJsonStreaming eventLogJsonStreaming(EventLogAsyncFacade eventLogAsyncFacade, Environment environment){
        String filePath = environment.getProperty("filePath");
        return new EventLogJsonStreaming(eventLogAsyncFacade, filePath);
    }
}
