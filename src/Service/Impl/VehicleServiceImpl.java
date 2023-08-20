package Service.Impl;

import DataObjects.VehicleDetails;
import DataStore.Impl.VehicleKeyValueDataStore;
import DataStore.VehicleDataStore;
import Service.VehicleService;

public class VehicleServiceImpl implements VehicleService {
    private static VehicleServiceImpl vehicleService;
    private VehicleServiceImpl() {}
    public static VehicleServiceImpl getInstance() {
        if(vehicleService == null) {
            vehicleService = new VehicleServiceImpl();
        }
        return vehicleService;
    }
    private static final VehicleDataStore vehicleDataStore = VehicleKeyValueDataStore.getInstance();

    @Override
    public void addVehicleToDataStore(VehicleDetails vehicleDetails) {
        System.out.println("Inserting Vehicle in Data Store: " + vehicleDetails);
        vehicleDataStore.addVehicleToDataStore(vehicleDetails);
        System.out.println("Vehicle added to Data Store.");
    }

    @Override
    public void updateVehicleInDataStore(VehicleDetails vehicleDetails) {
        System.out.println("Updating Vehicle in Data Store with values: " + vehicleDetails);
        vehicleDataStore.updateVehicleInDataStore(vehicleDetails);
        System.out.println("Vehicle updated in Data Store.");
    }

    @Override
    public VehicleDetails getVehicleFromDataStore(String vehicleName, String vehicleNumber) {
        System.out.println("Get Vehicle Info from Data Store: " + vehicleName);
        return vehicleDataStore.getVehicleFromDataStore(vehicleName, vehicleNumber);
    }

    @Override
    public void removeVehicleFromDataStore(String vehicleName) {
        System.out.println("Removing Vehicle from Data Store. " + vehicleName);
        vehicleDataStore.removeVehicleFromDataStore(vehicleName);
        System.out.println("Vehicle removed from Data Store.");
    }
}
