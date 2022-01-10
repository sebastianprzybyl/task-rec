package com.test.task.event.domain;

import lombok.Value;

@Value
class Alert {
    Boolean value;

    Alert(Long duration) {
        if (duration != null) {
            //TODO limit configurable from database from
            this.value = duration > 4;
        } else {
            this.value = false;
        }
    }
}