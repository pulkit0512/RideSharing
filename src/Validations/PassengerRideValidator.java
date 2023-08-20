package Validations;

import DataObjects.PassengerRideDetails;

public class PassengerRideValidator extends AbstractValidator<PassengerRideDetails>{

    static PassengerRideValidator passengerRideValidator;

    private PassengerRideValidator() {}

    public static PassengerRideValidator getInstance() {
        if (passengerRideValidator == null) {
            passengerRideValidator = new PassengerRideValidator();
        }
        return passengerRideValidator;
    }
    @Override
    public void validate(PassengerRideDetails req) throws Exception {
        boolean isPassengerPresent = checkUserInDataStore(req.getPassengerName());
        if (!isPassengerPresent)
            throw new Exception("Passenger does not exit in System.");
    }
}
