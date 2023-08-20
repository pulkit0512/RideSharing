package DataStore.Impl;

import DataObjects.DriverRideDetails;
import DataStore.RideDataStore;

import java.util.*;

public class RideKeyValueDataStore implements RideDataStore {

    static RideKeyValueDataStore rideKeyValueDataStore;
    private static final Map<String, Map<String, Set<String>>> rideDataStore = new HashMap<>();
    private static final Map<String, DriverRideDetails> rideInfoDataStore = new HashMap<>();
    private RideKeyValueDataStore(){}

    public static RideKeyValueDataStore getInstance() {
        if (rideKeyValueDataStore == null) {
            rideKeyValueDataStore = new RideKeyValueDataStore();
        }
        return rideKeyValueDataStore;
    }

    @Override
    public void addRideToDataStore(DriverRideDetails driverRideDetails) {
        rideDataStore.putIfAbsent(driverRideDetails.getOrigin(), new HashMap<>());
        rideDataStore.get(driverRideDetails.getOrigin()).putIfAbsent(driverRideDetails.getDestination(), new HashSet<>());

        String vehicleKey = driverRideDetails.getVehicleName()+"_"+driverRideDetails.getVehicleNumber();
        rideDataStore.get(driverRideDetails.getOrigin()).get(driverRideDetails.getDestination()).add(vehicleKey);
        rideInfoDataStore.put(vehicleKey, driverRideDetails);
    }

    @Override
    public void updateRideInDataStore(DriverRideDetails driverRideDetails) {
        String vehicleKey = driverRideDetails.getVehicleName()+"_"+driverRideDetails.getVehicleNumber();
        rideInfoDataStore.put(vehicleKey, driverRideDetails);
    }

    @Override
    public List<DriverRideDetails> getRideDetailsFromDataStore(String origin, String destination) {
        Map<String, Set<String>> destinationMap = rideDataStore.get(origin);
        if(destinationMap != null) {
            Set<String> vehicleKeys = destinationMap.get(destination);
            if(vehicleKeys != null) {
                List<DriverRideDetails> driverRideDetails = new ArrayList<>();
                for (String key : vehicleKeys) {
                    driverRideDetails.add(rideInfoDataStore.get(key));
                }
                return driverRideDetails;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void endRideFromDataStore(DriverRideDetails driverRideDetails) {
        Map<String, Set<String>> destinationMap = rideDataStore.get(driverRideDetails.getOrigin());
        if(destinationMap != null) {
            String vehicleKey = driverRideDetails.getVehicleName()+"_"+driverRideDetails.getVehicleNumber();
            Set<String> vehicleKeys = destinationMap.get(driverRideDetails.getDestination());
            if(vehicleKeys != null) {
                vehicleKeys.remove(vehicleKey);
                rideInfoDataStore.remove(vehicleKey);
            }
        }
    }
}
