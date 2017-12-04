package com.sqli.test.elevators.entities;

import java.util.Stack;

public interface IElevator {

    Stack<Elevator> init(int nbrFloors, String... elevators); //initilize elevators stack
    Elevator getElevatorById(String elevatorId); //get elevator by id
    void moveToUP(Elevator e); //move elevator to UP
    void moveToDown(Elevator e); //move elevator to DOWN
    String getClosestElevatorTo(int floor); //get the closest elevator to a specific floor
    String requestElevatorAtFloorZero();
}
