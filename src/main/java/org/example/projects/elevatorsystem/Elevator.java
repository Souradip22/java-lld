package org.example.projects.elevatorsystem;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator {
    private String id;
    private int currentFloor;
    private Queue<Integer> requests;
    private ElevatorState elevatorState;

    public Elevator(String id) {
        this.id = id;
        this.currentFloor = 0;
        this.requests = new LinkedList<>();
        this.elevatorState = ElevatorState.IDLE;
    }

    public void addRequest(int floor) {
        if (!this.requests.contains(floor)) {
            this.requests.offer(floor);
            System.out.println("Request added to Elevator " + id + " for floor " + floor);
        }
    }

    public void move() {
        if (requests.isEmpty()) {
            elevatorState = ElevatorState.IDLE;
            System.out.println("Elevator " + id + " is idle at floor: " + currentFloor);
            return;
        }
        int targetFloor = requests.peek();
        System.out.println("Elevator " + id + " starts moving from floor: " + currentFloor + " to floor: " + targetFloor);
        while (currentFloor != targetFloor) {
            if (currentFloor < targetFloor) {
                currentFloor++;
                elevatorState = ElevatorState.MOVING_UP;
            } else {
                currentFloor--;
                elevatorState = ElevatorState.MOVING_DOWN;
            }
            System.out.println("Elevator " + id + " at floor: " + currentFloor + " | State: " + elevatorState);
        }

        // Arrived at target floor
        requests.poll();
        openDoor();
        closeDoor();
    }

    public void run() {
        while (!requests.isEmpty()) {
            move();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        elevatorState = ElevatorState.IDLE;
        System.out.println("All requests completed. Elevator " + id + " is now idle.");
    }

    private void openDoor() {
        System.out.println("Opening door at floor: " + currentFloor + " in Elevator " + id);
    }

    private void closeDoor() {
        System.out.println("Closing door at floor: " + currentFloor + " in Elevator " + id);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Queue<Integer> getRequests() {
        return requests;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public String getId() {
        return id;
    }
}
