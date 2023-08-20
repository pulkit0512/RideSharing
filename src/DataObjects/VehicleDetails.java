package DataObjects;

public class VehicleDetails {
    private String userName;
    private String vehicleName;
    private String vehicleNumber;
    private boolean isRideOffered;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public boolean isRideOffered() {
        return isRideOffered;
    }

    public void setRideOffered(boolean rideOffered) {
        isRideOffered = rideOffered;
    }

    @Override
    public String toString() {
        return "[" + this.vehicleName + ", " + this.userName + "]";
    }
}
