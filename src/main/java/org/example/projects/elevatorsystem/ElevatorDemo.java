package org.example.projects.elevatorsystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorDemo {
    public static void main(String[] args) {
        List<Elevator> elevators = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            elevators.add(new Elevator("Ele-" + (i + 1)));
        }

        List<Request> requests = List.of(
                new Request(2, 4),
                new Request(1, 3),
                new Request(5, 0),
                new Request(6, 2)
        );

        for (Request req : requests) {
            Elevator assignedElevator = findOptimalElevator(elevators, req);
            System.out.println("Assigned Elevator " + assignedElevator.getId() + " to request from floor " + req.getSourceFloor() + " to " + req.getDestinationFloor());
            assignedElevator.addRequest(req.getSourceFloor());
            assignedElevator.addRequest(req.getDestinationFloor());
        }

        // Run all elevators
        for (Elevator elevator : elevators) {
            new Thread(elevator::run).start();
        }
    }

    private static Elevator findOptimalElevator(List<Elevator> elevators, Request req) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        // 1. Try idle elevators first (closest, least busy)
        for (Elevator elevator : elevators) {
            if (elevator.getElevatorState().equals(ElevatorState.IDLE)) {
                int distance = Math.abs(elevator.getCurrentFloor() - req.getSourceFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                } else if (distance == minDistance && bestElevator != null) {
                    // Tie-breaker: Pick the least busy elevator
                    if (elevator.getRequests().size() < bestElevator.getRequests().size()) {
                        bestElevator = elevator;
                    }
                }
            }
        }
        if (bestElevator != null) {
            System.out.println("Selected idle elevator: " + bestElevator.getId());
            return bestElevator;
        }

        // 2. Try elevators moving towards the request
        for (Elevator elevator : elevators) {
            if (movingInSameDirection(elevator, req)) {
                System.out.println("Selected moving elevator: " + elevator.getId());
                return elevator;
            }
        }

        // 3. Fallback: assign to elevator with least requests
        bestElevator = elevators.getFirst();
        for (Elevator elevator : elevators) {
            if (elevator.getRequests().size() < bestElevator.getRequests().size()) {
                bestElevator = elevator;
            }
        }
        System.out.println("Selected least busy elevator: " + bestElevator.getId());
        return bestElevator;
    }

    private static boolean movingInSameDirection(Elevator elevator, Request req) {
        if (elevator.getElevatorState().equals(ElevatorState.MOVING_UP)
                && elevator.getCurrentFloor() < req.getSourceFloor()) {
            return true;
        }
        if (elevator.getElevatorState().equals(ElevatorState.MOVING_DOWN)
                && elevator.getCurrentFloor() > req.getSourceFloor()) {
            return true;
        }
        return false;
    }
}
