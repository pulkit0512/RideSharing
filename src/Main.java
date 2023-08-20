import Controller.RideSharingController;
import DataObjects.DriverRideDetails;
import DataObjects.PassengerRideDetails;
import DataObjects.User;
import DataObjects.VehicleDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final File file = new File("/Users/pulkitarora/learning/RideSharing/input.txt");
    private static final Scanner sc;

    static {
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final RideSharingController rideSharingController = RideSharingController.getInstance();
    public static void main(String[] args) {
        System.out.println("Welcome to our Ride Sharing App!");
        while(true) {
            System.out.println("=========");
            System.out.println("For adding a new User, Press 1.");
            System.out.println("For adding a new Vehicle to existing user, Press 2.");
            System.out.println("For offering a ride, Press 3.");
            System.out.println("For selecting a ride, Press 4");
            System.out.println("For ending a ride, Press 5");
            System.out.println("For checking ride stats, Press 6");
            System.out.println("For exit, Press 0");
            System.out.println("=========");

            int requestType = sc.nextInt();
            if(requestType == 0) {
                System.out.println("Thank you!!");
                break;
            }

            switch (requestType) {
                case 1 -> addUser();
                case 2 -> addVehicle();
                case 3 -> offerRide();
                case 4 -> selectRide();
                case 5 -> endRide();
                case 6 -> getRideStats();
                default -> System.out.println("Not a valid input request PLEASE TRY AGAIN!!");
            }
        }
    }
    private static void addUser() {
        User user = new User();
        System.out.println("Enter user details [Name Gender(M/F) Age]: ");
        String name = sc.next().toUpperCase();
        char gender = sc.next().toUpperCase().charAt(0);
        int age = sc.nextInt();
        user.setName(name);
        user.setGender(gender);
        user.setAge(age);
        try {
            rideSharingController.addUser(user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void addVehicle() {
        VehicleDetails vehicleDetails = new VehicleDetails();
        System.out.println("Enter Vehicle details [UserName VehicleName VehicleNumber]: ");
        String userName = sc.next().toUpperCase();
        String vehicleName = sc.next().toUpperCase();
        String vehicleNumber = sc.next().toUpperCase();
        vehicleDetails.setUserName(userName);
        vehicleDetails.setVehicleName(vehicleName);
        vehicleDetails.setVehicleNumber(vehicleNumber);
        try {
            rideSharingController.addVehicle(vehicleDetails);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void offerRide() {
        DriverRideDetails driverRideDetails = getDriverRideDetails();
        driverRideDetails.setAction("ADD");
        try {
            rideSharingController.addRide(driverRideDetails);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void selectRide() {
        PassengerRideDetails passengerRideDetails = new PassengerRideDetails();
        System.out.println("Enter ride details [PassengerName Origin Destination RequestedSeats SelectionStrategy(VehicleName/MostVacant)]: ");
        passengerRideDetails.setPassengerName(sc.next().toUpperCase());
        passengerRideDetails.setOrigin(sc.next().toUpperCase());
        passengerRideDetails.setDestination(sc.next().toUpperCase());
        passengerRideDetails.setRequestedSeats(sc.nextInt());
        passengerRideDetails.setSelectionStrategy(sc.next().toUpperCase());

        try {
            rideSharingController.selectRide(passengerRideDetails);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void endRide() {
        DriverRideDetails driverRideDetails = getDriverRideDetails();
        driverRideDetails.setAction("END");
        try {
            rideSharingController.endRide(driverRideDetails);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void getRideStats() {
        RideSharingController rideSharingController = RideSharingController.getInstance();
        List<User> userStats = rideSharingController.getRideStats();
        userStats.forEach(user -> System.out.println(user.getName() + ": " + user.getRideTaken() + " Taken, " + user.getRideOffered() + " Offered."));
    }

    private static DriverRideDetails getDriverRideDetails() {
        DriverRideDetails driverRideDetails = new DriverRideDetails();
        System.out.println("Enter ride details [DriverName Origin AvailableSeats VehicleName VehicleNumber Destination]: ");
        driverRideDetails.setDriverName(sc.next().toUpperCase());
        driverRideDetails.setOrigin(sc.next().toUpperCase());
        driverRideDetails.setAvailableSeats(sc.nextInt());
        driverRideDetails.setVehicleName(sc.next().toUpperCase());
        driverRideDetails.setVehicleNumber(sc.next().toUpperCase());
        driverRideDetails.setDestination(sc.next().toUpperCase());
        return driverRideDetails;
    }
}