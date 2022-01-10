package com.test.task.event.domain;

import lombok.Value;

@Value
class Duration {
    Long value;

    Duration(Long timestamp, Long timestamp2) {
        if (timestamp != null && timestamp2 != null) {
            this.value = Math.abs(Math.subtractExact(timestamp, timestamp2));
        } else {
            this.value = 0L;
        }
    }
}
