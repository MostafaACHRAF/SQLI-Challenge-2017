package com.sqli.test.elevators;

import com.sqli.test.elevators.entities.Elevator;
import com.sqli.test.elevators.config.Configuration;

import java.util.*;
import java.util.logging.Logger;

public class Building {

    private Stack<Elevator> elevators;
    private int numberOfFloors;
    private Logger logger = Logger.getGlobal(); //log informations about the code
    private ElevatorsManager elevatorsManager = new ElevatorsManager(this.numberOfFloors); //the manager of elevators

    /**
     * @param numberOfFloors: the number of floors in the building
     * @param elevators: an array of descriptions of the elevators of the building. 
     *                   A description has the following format "[elevator_id]:[elevator_current_floor]".
     */
    public Building(int numberOfFloors, String... elevators) {

        this.elevators = elevatorsManager.init(numberOfFloors, elevators); //initialize the elevators stack
        this.numberOfFloors = numberOfFloors; //initialize number of floors

    }

    /**
     * Request an elevator at floor zero.
     * @return the id of the elevator that should serve the request.
     */
    public String requestElevator() {
        return elevatorsManager.requestElevatorAtFloorZero(); //get the requested elevator at floor zero
    }

    /**
     * Request an elevator at floor indicate by the {@code floor} param.
     * @param floor : the floor where the request is made.
     * @return the id of the elevator that should serve the request.
     */
    public String requestElevator(int floor) {
        return elevatorsManager.getClosestElevatorTo(floor); //get the requested elevator at floor X
    }

    /**
     * Request the elevator with the id {@code elevatorId} to stop at the floor indicated by the {@code floor} param.
     * @param elevatorId : the id of the elevator to whom give the order.
     * @param floor : the floor at which the elevator should stop
     */
    public void stopAt(String elevatorId, int floor) {

        Elevator e = elevatorsManager.getElevatorById(elevatorId);

        elevatorsManager.stopAtFloor(e, floor);

        Collections.sort(this.elevators);

//        for (Elevator ee: this.elevators) { logger.info(ee.toString());}
    }

    /**
     * Move the elevator with the id {@code elevatorId} in the direction indicated by the {@code direction} param.
     * @param elevatorId : the id of the elevator to move.
     * @param direction : the direction to go. Can be "UP" or "DOWN".
     */
    public void move(String elevatorId, String direction) {

        Elevator e = elevatorsManager.getElevatorById(elevatorId);

        if (!e.hasSameDirectionAs(direction) && !e.isInitState()) {
            logger.info("ERROR you can not switch his direction in the middle of a building !!!");
            return;
        }

        if (direction.equals(Configuration.UP)) {
            elevatorsManager.moveToUP(e); //move to up
        }

        else if (direction.equals(Configuration.DOWN)) {
            elevatorsManager.moveToDown(e); //move to down
        }

        Collections.sort(this.elevators);

    }

}
