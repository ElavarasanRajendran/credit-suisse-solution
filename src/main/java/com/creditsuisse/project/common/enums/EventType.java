package com.creditsuisse.project.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventType {
    APPLICATION_LOG("APPLICATION_LOG"),
    UNKNOWN("UNKNOWN");

    private final String eventType;
}
