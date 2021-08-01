package com.creditsuisse.project.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventState {
    STARTED("STARTED"),
    FINISHED("FINISHED");

    private final String eventState;
}
