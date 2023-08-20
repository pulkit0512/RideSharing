package Service;

import DataObjects.User;

import java.util.List;

public interface UserService {
    void addUserToDataStore(User user);
    void updateUserInDataStore(User user);
    User getUserFromDataStore(String name);
    void removeUserFromDataStore(String name);
    List<User> getRideStats();
}
