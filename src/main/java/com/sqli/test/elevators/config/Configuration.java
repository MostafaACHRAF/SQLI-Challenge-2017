package com.sqli.test.elevators.config;

import com.sqli.test.elevators.entities.Elevator;

public interface Configuration {
    String UP = "UP";
    String DOWN = "DOWN";
    String RESTING = "RESTING";
    String DEFAULT_DIRECTION = "";
    String DESCRIPTION_SEPARATOR = ":";
    int ELEVATOR_ID_INDEX = 0;
    int ELEVATOR_CURRENT_FLOOR_INDEX = 1;
}
