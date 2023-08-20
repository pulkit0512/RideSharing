package DataObjects;

public class User {
    private String name;
    private char gender;
    private int age;
    private int rideTaken;
    private int rideOffered;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRideTaken() {
        return rideTaken;
    }

    public void setRideTaken(int rideTaken) {
        this.rideTaken = rideTaken;
    }

    public int getRideOffered() {
        return rideOffered;
    }

    public void setRideOffered(int rideOffered) {
        this.rideOffered = rideOffered;
    }

    @Override
    public String toString() {
        return "[" + this.name + ", " + this.gender + ", " + this.age + "]";
    }
}
