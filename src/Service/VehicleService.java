package Service;

import DataObjects.VehicleDetails;

public interface VehicleService {
    void addVehicleToDataStore(VehicleDetails vehicleDetails);
    void updateVehicleInDataStore(VehicleDetails vehicleDetails);
    VehicleDetails getVehicleFromDataStore(String vehicleName, String vehicleNumber);
    void removeVehicleFromDataStore(String vehicleName);
}
