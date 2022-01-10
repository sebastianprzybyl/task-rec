package com.test.task.event.json;

import com.test.task.event.domain.EventLogAsyncFacade;
import com.test.task.event.dto.EventLogDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class EventLogJsonStreaming {
    //TODO create @util class or use some mapper to cast object
    private static final String ID = "id";
    private static final String STATE = "state";
    private static final String TYPE = "type";
    private static final String HOST = "host";
    private static final String TIMESTAMP = "timestamp";

    private final EventLogAsyncFacade eventLogFacade;
    private final String filePath;

    //TODO handle not found exception
    public void readAndProcessFile() {
        File jsonFilePath = null;
        try {
            jsonFilePath = ResourceUtils.getFile(filePath);
            process(jsonFilePath);
        } catch (FileNotFoundException e) {
            log.error("Wrong file path");
        }
    }

    private void passEventLogToFacade(EventLogJson eventLogJson) {
        EventLogDto eventLogDto = eventLogJson.dto();
        eventLogFacade.handleEventLog(eventLogDto);
    }

    //TODO ugly method, split responsibility of this method
    private void process(File jsonFilePath) {
        File jsonFile = jsonFilePath;
        JsonFactory jsonfactory = new JsonFactory();
        try {
            int numberOfRecords = 0;
            JsonParser jsonParser = jsonfactory.createJsonParser(jsonFile);
            EventLogJson eventLogJson = new EventLogJson();

            JsonToken jsonToken = jsonParser.nextToken();
            while (jsonToken != null) {
                String fieldname = jsonParser.getCurrentName();
                if (ID.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    eventLogJson.setId(jsonParser.getText());
                }
                if (STATE.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    eventLogJson.setState(jsonParser.getText());
                }
                if (TYPE.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    eventLogJson.setType(jsonParser.getText());
                }
                if (HOST.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    eventLogJson.setHost(jsonParser.getText());
                }
                if (TIMESTAMP.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    eventLogJson.setTimestamp(Long.parseLong(jsonParser.getText()));
                }

                if (jsonToken == JsonToken.END_OBJECT) {
                    passEventLogToFacade(eventLogJson);
                    eventLogJson = new EventLogJson();
                    numberOfRecords++;
                }
                jsonToken = jsonParser.nextToken();
            }
            log.info("Total Records Found : " + numberOfRecords);
        } catch (IOException e) {
            log.error("Error when file reading");
        }
    }
}
