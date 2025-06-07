package org.example.projects.airlinetrafficmanagement;

public interface Mediator {
    void registerAirplane(Airplane airplane);
    void handleTakeoffRequest(Airplane airplane);
    void handleLandingRequest(Airplane airplane);
}
