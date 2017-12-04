package com.sqli.test.elevators.entities;

import com.sqli.test.elevators.config.Configuration;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class Elevator implements Comparable<Elevator> {

    private String id;
    private int currentFloor;
    private String direction;

    public Elevator() {
    }

    public Elevator(String id, int currentFloor, String direction) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + currentFloor + ", " + direction + ")";
    }

    @Override
    public int compareTo(Elevator o) {
        return this.currentFloor - o.getCurrentFloor();
    }

    //calculate the distance between the currentFloor and the target one
    public int getDistance(int floor) {
        return Math.abs(this.currentFloor - floor);
    }

    //is the elevator in the default state
    public Boolean isInitState() {
        return this.getDirection().equals("");
    }

    //is the elevator direction is UP
    public Boolean isUp() {
        return this.direction.equals(Configuration.UP);
    }

    //is the elevator direction is DOWN
    public Boolean isDown() {
        return this.direction.equals(Configuration.DOWN);
    }

    //Is the elevator stoped
    public Boolean isResting() {
        return this.direction.equals(Configuration.RESTING);
    }

    //is the elevator has the same direction as ...
    public Boolean hasSameDirectionAs(String direction) {
        return this.direction.equals(direction);
    }

    //is the elevator in the top floor
    public Boolean inToPFloor(int nbrFloors) {
        return this.currentFloor + 1 == nbrFloors - 1;
    }

    //is the elevator in the bottom floor
    public Boolean inZeroFloor(int nbrFloors) {
        return this.currentFloor - 1 == 0;
    }

}
