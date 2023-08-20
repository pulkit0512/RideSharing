package Service.Impl;

import DataObjects.User;
import DataStore.Impl.UserKeyValueDataStore;
import DataStore.UserDataStore;
import Service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService;
    private UserServiceImpl() {}
    public static UserServiceImpl getInstance() {
        if(userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
    private static final UserDataStore userDataStore = UserKeyValueDataStore.getInstance();
    @Override
    public void addUserToDataStore(User user) {
        System.out.println("Inserting User in Data Store: " + user);
        userDataStore.addUserToDataStore(user);
        System.out.println("User added to Data Store.");
    }

    @Override
    public void updateUserInDataStore(User user) {
        System.out.println("Updating User in Data Store with values: " + user);
        userDataStore.updateUserToDataStore(user);
        System.out.println("User updated in Data Store.");
    }

    @Override
    public User getUserFromDataStore(String name) {
        System.out.println("Get User Info from Data Store: " + name);
        return userDataStore.getUserFromDataStore(name);
    }

    @Override
    public void removeUserFromDataStore(String name) {
        System.out.println("Removing User from Data Store. " + name);
        userDataStore.removeUserFromDataStore(name);
        System.out.println("User removed from Data Store.");
    }

    @Override
    public List<User> getRideStats() {
        return userDataStore.getUserStats();
    }
}
