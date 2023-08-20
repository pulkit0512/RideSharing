package Controller;

import DataObjects.DriverRideDetails;
import DataObjects.PassengerRideDetails;
import DataObjects.User;
import DataObjects.VehicleDetails;
import Service.Impl.RideServiceImpl;
import Service.Impl.UserServiceImpl;
import Service.Impl.VehicleServiceImpl;
import Service.RideService;
import Service.UserService;
import Service.VehicleService;
import Validations.DriverRideValidator;
import Validations.PassengerRideValidator;
import Validations.UserValidations;
import Validations.VehicleValidator;

import java.util.List;

public class RideSharingController {
    private static final UserValidations userValidations = UserValidations.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final VehicleValidator vehicleValidator = VehicleValidator.getInstance();
    private static final VehicleService vehicleService = VehicleServiceImpl.getInstance();
    private static final DriverRideValidator driverRideValidator = DriverRideValidator.getInstance();
    private static final PassengerRideValidator passengerRideValidator = PassengerRideValidator.getInstance();
    private static final RideService rideService = RideServiceImpl.getInstance();
    static RideSharingController rideSharingController;

    private RideSharingController(){}

    public static RideSharingController getInstance() {
        if (rideSharingController == null) {
            rideSharingController = new RideSharingController();
        }
        return rideSharingController;
    }

    public void addUser(User user) throws Exception {
        userValidations.validate(user);
        userService.addUserToDataStore(user);
    }

    public void addVehicle(VehicleDetails vehicleDetails) throws Exception {
        vehicleValidator.validate(vehicleDetails);
        vehicleService.addVehicleToDataStore(vehicleDetails);
    }

    public void addRide(DriverRideDetails driverRideDetails) throws Exception {
        driverRideValidator.validate(driverRideDetails);
        rideService.addRide(driverRideDetails);
    }

    public void selectRide(PassengerRideDetails passengerRideDetails) throws Exception {
        passengerRideValidator.validate(passengerRideDetails);
        rideService.selectRide(passengerRideDetails);
    }

    public void endRide(DriverRideDetails driverRideDetails) throws Exception {
        driverRideValidator.validate(driverRideDetails);
        rideService.endRide(driverRideDetails);
    }

    public List<User> getRideStats() {
        UserService userService = UserServiceImpl.getInstance();
        return userService.getRideStats();
    }
}
