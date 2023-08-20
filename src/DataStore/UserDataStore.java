package DataStore;

import DataObjects.User;

import java.util.List;

public interface UserDataStore {
    void addUserToDataStore(User user);
    void removeUserFromDataStore(String name);
    void updateUserToDataStore(User user);
    User getUserFromDataStore(String name);
    List<User> getUserStats();
}
