package DataStore;

import DataObjects.DriverRideDetails;

import java.util.List;

public interface RideDataStore {
    void addRideToDataStore(DriverRideDetails driverRideDetails);
    void updateRideInDataStore(DriverRideDetails driverRideDetails);
    List<DriverRideDetails> getRideDetailsFromDataStore(String origin, String destination);
    void endRideFromDataStore(DriverRideDetails driverRideDetails);
}
