package com.creditsuisse.project.common.dto;

import com.creditsuisse.project.common.enums.EventState;
import com.creditsuisse.project.common.enums.EventType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Event {

    private String id;
    private EventState state;
    private long timestamp;

    @JsonIgnoreProperties
    private EventType type;

    @JsonIgnoreProperties
    private String host;
}
