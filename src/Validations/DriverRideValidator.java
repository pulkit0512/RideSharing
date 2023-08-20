package Validations;

import DataObjects.DriverRideDetails;

public class DriverRideValidator extends AbstractValidator<DriverRideDetails> {
    static DriverRideValidator driverRideValidator;

    private DriverRideValidator() {}

    public static DriverRideValidator getInstance() {
        if (driverRideValidator == null) {
            driverRideValidator = new DriverRideValidator();
        }
        return driverRideValidator;
    }
    @Override
    public void validate(DriverRideDetails req) throws Exception {
        validateRideDetails(req);

        if (req.getAction().equals("ADD"))
            validateAddRide(req);
        else
            validateEndRide(req);
    }

    private void validateRideDetails(DriverRideDetails driverRideDetails) throws Exception {
        boolean isDriverPresent = checkUserInDataStore(driverRideDetails.getDriverName());
        if (!isDriverPresent)
            throw new Exception("Driver does not exit in System.");
        boolean isVehicleValid = checkVehicleDetailsInDataStore(driverRideDetails.getVehicleName(), driverRideDetails.getVehicleNumber());
        if (!isVehicleValid)
            throw new Exception("Vehicle does not exist.");
        boolean isVehicleDriverMatch = checkVehicleDriverDetailsInDataStore(driverRideDetails);
        if (!isVehicleDriverMatch)
            throw new Exception("Driver and Vehicle does not match.");
    }

    private void validateAddRide(DriverRideDetails driverRideDetails) throws Exception {
        boolean isRideOffered = isVehicleAlreadyOfferedRide(driverRideDetails.getVehicleName(), driverRideDetails.getVehicleNumber());
        if (isRideOffered)
            throw new Exception("Ride already offered for this vehicle");
    }

    private void validateEndRide(DriverRideDetails driverRideDetails) throws Exception {
        boolean isRideOffered = isVehicleAlreadyOfferedRide(driverRideDetails.getVehicleName(), driverRideDetails.getVehicleNumber());
        if (!isRideOffered)
            throw new Exception("No existing ride to end for this vehicle.");
    }
}
