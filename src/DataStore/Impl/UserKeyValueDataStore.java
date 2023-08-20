package DataStore.Impl;

import DataObjects.User;
import DataStore.UserDataStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserKeyValueDataStore implements UserDataStore {
    static UserKeyValueDataStore userKeyValueDataStore;
    private static final Map<String, User> userDataStore = new HashMap<>();
    private UserKeyValueDataStore(){}

    public static UserKeyValueDataStore getInstance() {
        if (userKeyValueDataStore == null) {
            userKeyValueDataStore = new UserKeyValueDataStore();
        }
        return userKeyValueDataStore;
    }

    @Override
    public void addUserToDataStore(User user) {
        userDataStore.put(user.getName(), user);
    }

    @Override
    public void removeUserFromDataStore(String name) {
        userDataStore.remove(name);
    }

    @Override
    public void updateUserToDataStore(User user) {
        addUserToDataStore(user);
    }

    @Override
    public User getUserFromDataStore(String name) {
        return userDataStore.get(name);
    }

    @Override
    public List<User> getUserStats() {
        return new ArrayList<>(userDataStore.values());
    }
}
