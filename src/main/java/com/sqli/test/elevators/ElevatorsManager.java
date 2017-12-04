package com.sqli.test.elevators;

import com.sqli.test.elevators.config.Configuration;
import com.sqli.test.elevators.entities.Elevator;
import com.sqli.test.elevators.entities.IElevator;

import java.util.Collections;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class ElevatorsManager implements IElevator {

    private int nbrFloors;
    private Stack<Elevator> allElevators;

    public ElevatorsManager(int nbrFloors) {
        this.nbrFloors = nbrFloors;
    }

    //initilize Elevators stack
    public Stack<Elevator> init(int nbrFloors, String... elevators) {

        Stack<Elevator> stackElevators = new Stack<Elevator>();
        this.nbrFloors = nbrFloors;

        for (String elevator : elevators) {

            Elevator e = ElevatorFactory.newInstance(elevator); //create new instance of Elevator using ELEVATOR_DESCRIPTION

            stackElevators.push(e); //add the instance to elevators of the building
        }

        Collections.sort(stackElevators); // sort these elevators with currentFloor

        allElevators = stackElevators;

        return stackElevators;
    }


    //get elevator by id
    public Elevator getElevatorById(String elevatorId) {

        Elevator target = null;

        for (Elevator e : allElevators) {
            if (e.getId().equals(elevatorId)) {
                target = e;
            }
        }

        return target;
    }

    //move elevator to UP !
    public void moveToUP(Elevator e) {
        if (e.inToPFloor(nbrFloors)) {
            e.setDirection(Configuration.DOWN); //switch elevator direction to down
        }
        e.setCurrentFloor(e.getCurrentFloor() + 1);
        e.setDirection(Configuration.UP);
    }

    public void moveToDown(Elevator e) {
        if (e.inZeroFloor(nbrFloors)) {
            e.setDirection(Configuration.UP); //switch elevator direction to up
        }
        e.setCurrentFloor(e.getCurrentFloor() - 1);
        e.setDirection(Configuration.DOWN);
    }

    public void stopAtFloor(Elevator e, int floor) {
        e.setCurrentFloor(floor);
        e.setDirection(Configuration.RESTING);
    }

    //move elevator to down !
    public String getClosestElevatorTo(int floor) {
        Elevator e = null;
        int minD = nbrFloors - 1;
        Elevator requestedElevator = null;
        Stack<Elevator> tmpElevators = allElevators;

        while(!tmpElevators.isEmpty()) {
            e = tmpElevators.pop();

            if (e.getDistance(floor) < minD) {
                minD = e.getDistance(floor);
                requestedElevator = e;
            }
        }

        return requestedElevator != null ? requestedElevator.getId() : "";
    }

    // get the id of the available elevator to floor zero
    public String requestElevatorAtFloorZero() {

        Elevator ee = null;
        Elevator requestedElevator = null;
        Stack<Elevator> tmpElevators = allElevators;

        while (!tmpElevators.isEmpty()) {
            ee = tmpElevators.pop();
            if (ee.isUp() || ee.isInitState()) {
                requestedElevator = ee;
                break;
            }
        }

        return requestedElevator != null ? requestedElevator.getId() : "....!";

    }

    public int getNbrFloors() {
        return nbrFloors;
    }

    public void setNbrFloors(int nbrFloors) {
        this.nbrFloors = nbrFloors;
    }
}
