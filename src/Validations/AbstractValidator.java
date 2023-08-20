package Validations;

import DataObjects.DriverRideDetails;
import DataObjects.VehicleDetails;
import DataStore.Impl.UserKeyValueDataStore;
import DataStore.Impl.VehicleKeyValueDataStore;

public abstract class AbstractValidator<R> implements Validator<R> {
    private static final UserKeyValueDataStore userKeyValueDataStore = UserKeyValueDataStore.getInstance();
    private static final VehicleKeyValueDataStore vehicleKeyValueDataStore = VehicleKeyValueDataStore.getInstance();

    protected boolean checkUserInDataStore(String name) {
        return userKeyValueDataStore.getUserFromDataStore(name) != null;
    }

    protected boolean checkVehicleDetailsInDataStore(String vehicleName, String vehicleNumber) {
        return vehicleKeyValueDataStore.getVehicleFromDataStore(vehicleName, vehicleNumber) != null;
    }

    protected boolean checkVehicleDriverDetailsInDataStore(DriverRideDetails driverRideDetails) {
        VehicleDetails vehicleDetails =  vehicleKeyValueDataStore.getVehicleFromDataStore(driverRideDetails.getVehicleName(),
                driverRideDetails.getVehicleNumber());

        return vehicleDetails.getUserName().equalsIgnoreCase(driverRideDetails.getDriverName());
    }

    protected boolean isVehicleAlreadyOfferedRide(String vehicleName, String vehicleNumber) {
        VehicleDetails vehicleDetails = vehicleKeyValueDataStore.getVehicleFromDataStore(vehicleName, vehicleNumber);
        return vehicleDetails.isRideOffered();
    }
}
