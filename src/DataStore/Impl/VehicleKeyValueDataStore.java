package DataStore.Impl;

import DataObjects.VehicleDetails;
import DataStore.VehicleDataStore;

import java.util.HashMap;
import java.util.Map;

public class VehicleKeyValueDataStore implements VehicleDataStore {
    private static VehicleKeyValueDataStore vehicleKeyValueDataStore;
    private static final Map<String, VehicleDetails> vehicleDataStore = new HashMap<>();
    private VehicleKeyValueDataStore(){}

    public static VehicleKeyValueDataStore getInstance() {
        if (vehicleKeyValueDataStore == null) {
            vehicleKeyValueDataStore = new VehicleKeyValueDataStore();
        }
        return vehicleKeyValueDataStore;
    }

    @Override
    public void addVehicleToDataStore(VehicleDetails vehicleDetails) {
        vehicleDataStore.put(vehicleDetails.getVehicleName() + "_" +vehicleDetails.getVehicleNumber(), vehicleDetails);
    }

    @Override
    public void removeVehicleFromDataStore(String vehicleName) {
        vehicleDataStore.remove(vehicleName);
    }

    @Override
    public void updateVehicleInDataStore(VehicleDetails vehicleDetails) {
        addVehicleToDataStore(vehicleDetails);
    }

    @Override
    public VehicleDetails getVehicleFromDataStore(String vehicleName, String vehicleNumber) {
        return vehicleDataStore.get(vehicleName + "_" + vehicleNumber);
    }
}
