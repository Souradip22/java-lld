package org.example.projects.airlinetrafficmanagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ControlTower controlTower = new ControlTower();

        String airplaneId1 = sc.nextLine();
        String airplaneId2 = sc.nextLine();
        String airplaneId3 = sc.nextLine();
        String airplaneId4 = sc.nextLine();

        // TODO: Instantiate airplanes with the provided IDs
        Airplane airplane1 = new Airplane(airplaneId1);
        Airplane airplane2 = new Airplane(airplaneId2);
        Airplane airplane3 = new Airplane(airplaneId3);
        Airplane airplane4 = new Airplane(airplaneId4);


        // TODO: Register the airplanes with the control tower
        controlTower.registerAirplane(airplane1);
        controlTower.registerAirplane(airplane2);
        controlTower.registerAirplane(airplane3);
        controlTower.registerAirplane(airplane4);


        airplane1.requestTakeoff();
        airplane2.requestTakeoff();
        airplane3.requestTakeoff();
        airplane4.requestTakeoff();

        // TODO: Mark the first airplane as having completed takeoff and free a runway
        controlTower.completeTakeoff(airplane1);


        // TODO: Mark the second airplane as having completed takeoff and free a runway
        controlTower.completeTakeoff(airplane2);


        airplane3.requestTakeoff();
        airplane4.requestTakeoff();

        // TODO: Mark the third airplane as having completed takeoff and free a runway
        controlTower.completeTakeoff(airplane3);


        // TODO: Mark the fourth airplane as having completed takeoff and free a runway
        controlTower.completeTakeoff(airplane4);

        airplane1.requestLanding();
        airplane2.requestLanding();

        // TODO: Mark the first airplane as having completed landing and free a runway
        controlTower.completeLanding(airplane1);


        // TODO: Mark the second airplane as having completed landing and free a runway
        controlTower.completeLanding(airplane2);

        airplane3.requestLanding();
        airplane4.requestLanding();

        // TODO: Mark the third airplane as having completed landing and free a runway
        controlTower.completeLanding(airplane3);


        // TODO: Mark the fourth airplane as having completed landing and free a runway
        controlTower.completeLanding(airplane4);



        sc.close();
    }
}
