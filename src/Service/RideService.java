package Service;

import DataObjects.DriverRideDetails;
import DataObjects.PassengerRideDetails;

public interface RideService {
    void addRide(DriverRideDetails driverRideDetails);
    void endRide(DriverRideDetails driverRideDetails);
    void selectRide(PassengerRideDetails passengerRideDetails);
}
