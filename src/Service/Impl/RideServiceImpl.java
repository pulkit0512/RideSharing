package Service.Impl;

import DataObjects.DriverRideDetails;
import DataObjects.PassengerRideDetails;
import DataObjects.User;
import DataObjects.VehicleDetails;
import DataStore.Impl.RideKeyValueDataStore;
import DataStore.Impl.UserKeyValueDataStore;
import DataStore.Impl.VehicleKeyValueDataStore;
import Service.RideService;

import java.util.List;

public class RideServiceImpl implements RideService {
    private static RideServiceImpl rideService;
    private RideServiceImpl() {}
    public static RideServiceImpl getInstance() {
        if(rideService == null) {
            rideService = new RideServiceImpl();
        }
        return rideService;
    }
    private static final RideKeyValueDataStore rideDataStore = RideKeyValueDataStore.getInstance();
    private static final VehicleKeyValueDataStore vehicleDataStore = VehicleKeyValueDataStore.getInstance();
    private static final UserKeyValueDataStore userDataStore = UserKeyValueDataStore.getInstance();
    @Override
    public void addRide(DriverRideDetails driverRideDetails) {
        System.out.println("Adding ride in Data Store.");
        rideDataStore.addRideToDataStore(driverRideDetails);
        VehicleDetails vehicleDetails = vehicleDataStore.getVehicleFromDataStore(driverRideDetails.getVehicleName(),
                driverRideDetails.getVehicleNumber());

        vehicleDetails.setRideOffered(true);
        vehicleDataStore.updateVehicleInDataStore(vehicleDetails);

        User user = userDataStore.getUserFromDataStore(driverRideDetails.getDriverName());
        user.setRideOffered(user.getRideOffered() + 1);
        userDataStore.updateUserToDataStore(user);
        System.out.println("Ride added in Data Store Successfully.");
    }

    @Override
    public void endRide(DriverRideDetails driverRideDetails) {
        System.out.println("Ending ride from Data Store.");
        rideDataStore.endRideFromDataStore(driverRideDetails);
        VehicleDetails vehicleDetails = vehicleDataStore.getVehicleFromDataStore(driverRideDetails.getVehicleName(),
                driverRideDetails.getVehicleNumber());

        vehicleDetails.setRideOffered(false);
        vehicleDataStore.updateVehicleInDataStore(vehicleDetails);
        System.out.println("Ride ended in Data Store.");
    }

    @Override
    public void selectRide(PassengerRideDetails passengerRideDetails) {
        System.out.println("Selecting ride for the passenger.");
        List<DriverRideDetails> rideDetails = rideDataStore.getRideDetailsFromDataStore(passengerRideDetails.getOrigin(),
                passengerRideDetails.getDestination());
        if(rideDetails.isEmpty()) {
            System.out.println("No Ride Found.");
            return;
        }
        String selectionStrategy = passengerRideDetails.getSelectionStrategy();
        int requestedSeats = passengerRideDetails.getRequestedSeats();

        DriverRideDetails ride = null;
        if (selectionStrategy.equals("MOSTVACANT")) {
            for (DriverRideDetails driverRideDetail : rideDetails) {
                if(driverRideDetail.getAvailableSeats()>=requestedSeats) {
                    if(ride == null || ride.getAvailableSeats() < driverRideDetail.getAvailableSeats()) {
                        ride = driverRideDetail;
                    }
                }
            }
        } else {
            for (DriverRideDetails driverRideDetail : rideDetails) {
                if(driverRideDetail.getAvailableSeats()>=requestedSeats && driverRideDetail.getVehicleName().equals(selectionStrategy)) {
                    ride = driverRideDetail;
                    break;
                }
            }
        }

        if (ride == null) {
            System.out.println("No Ride Found.");
            return;
        }

        ride.setAvailableSeats(ride.getAvailableSeats() - requestedSeats);
        rideDataStore.updateRideInDataStore(ride);

        User user = userDataStore.getUserFromDataStore(passengerRideDetails.getPassengerName());
        user.setRideTaken(user.getRideTaken() + 1);
        userDataStore.updateUserToDataStore(user);
        System.out.println("Ride Found. With Vehicle Details: " + ride);
    }
}
