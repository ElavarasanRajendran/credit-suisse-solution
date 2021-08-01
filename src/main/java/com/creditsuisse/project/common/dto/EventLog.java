package com.creditsuisse.project.common.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
public class EventLog {

    private Event startEvent;
    private Event endEvent;

    private EventLog(Event startEvent, Event endEvent) {
        this.startEvent = startEvent;
        this.endEvent = endEvent;
    }

    public static EventLog newInstance(Event startEvent, Event endEvent) {
        return new EventLog(startEvent, endEvent);
    }

    public Long getDuration() {
        long duration = 0;
        if(Objects.nonNull(startEvent) && Objects.nonNull(endEvent)) {
            duration = endEvent.getTimestamp() - startEvent.getTimestamp();
        }
        return duration;
    }

    public boolean isLongRunningEvent() {
        return !(getDuration() <= 4);
    }
}
