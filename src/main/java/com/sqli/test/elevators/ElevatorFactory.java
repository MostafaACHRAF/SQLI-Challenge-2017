package com.sqli.test.elevators;

import com.sqli.test.elevators.config.Configuration;
import com.sqli.test.elevators.entities.Elevator;

public class ElevatorFactory {

    public static Elevator newInstance(String elevator_desc) {

        String id = elevator_desc.split(Configuration.DESCRIPTION_SEPARATOR)[Configuration.ELEVATOR_ID_INDEX];
        int currentFloor = Integer.parseInt(elevator_desc.split(Configuration.DESCRIPTION_SEPARATOR)[Configuration.ELEVATOR_CURRENT_FLOOR_INDEX]);

        return new Elevator(id, currentFloor, Configuration.DEFAULT_DIRECTION);

    }
}
