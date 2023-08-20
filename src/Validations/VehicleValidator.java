package Validations;

import DataObjects.VehicleDetails;

public class VehicleValidator extends AbstractValidator<VehicleDetails> {
    private static VehicleValidator vehicleValidator;
    private VehicleValidator(){}

    public static VehicleValidator getInstance() {
        if(vehicleValidator == null) {
            vehicleValidator = new VehicleValidator();
        }
        return vehicleValidator;
    }
    @Override
    public void validate(VehicleDetails req) throws Exception {
        validateVehicleDetails(req);
    }

    private void validateVehicleDetails(VehicleDetails vehicleDetails) throws Exception {
        boolean isUserPresent = checkUserInDataStore(vehicleDetails.getUserName());
        if (!isUserPresent)
            throw new Exception("UserName does not exist.");
        boolean isVehiclePresent = checkVehicleDetailsInDataStore(vehicleDetails.getVehicleName(), vehicleDetails.getVehicleNumber());
        if (isVehiclePresent)
            throw new Exception("Vehicle already registered.");
    }
}
