package org.example.designpatterns.projects.airlinetrafficmanagement;

public class Airplane {
    private String id;
    private Mediator mediator;

    public Airplane(String id) {
        this.id = id;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void requestTakeoff() {
        System.out.println("Airplane " + id + " requesting takeoff");

        // TODO: Notify the mediator to handle the takeoff request for this airplane
        this.mediator.handleTakeoffRequest(this);

    }

    public void requestLanding() {
        System.out.println("Airplane " + id + " requesting landing");

        // TODO: Notify the mediator to handle the landing request for this airplane
        this.mediator.handleLandingRequest(this);

    }

    public void receiveNotification(String message) {
        System.out.println("Airplane " + id + ": " + message);
    }

    public String getId() {
        return id;
    }
}
