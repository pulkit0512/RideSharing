package DataStore;


import DataObjects.VehicleDetails;

public interface VehicleDataStore {
    void addVehicleToDataStore(VehicleDetails vehicleDetails);
    void removeVehicleFromDataStore(String vehicleName);
    void updateVehicleInDataStore(VehicleDetails vehicleDetails);
    VehicleDetails getVehicleFromDataStore(String vehicleName, String vehicleNumber);
}
