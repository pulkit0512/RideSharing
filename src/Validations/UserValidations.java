package Validations;

import DataObjects.User;

public class UserValidations extends AbstractValidator<User>{
    static UserValidations userValidations;

    private UserValidations() {}

    public static UserValidations getInstance() {
        if (userValidations == null) {
            userValidations = new UserValidations();
        }
        return userValidations;
    }

    @Override
    public void validate(User req) throws Exception {
        validateUser(req);
    }

    private void validateUser(User user) throws Exception {
        boolean isUserPresent = checkUserInDataStore(user.getName());
        if (isUserPresent)
            throw new Exception("User Already exist.");
        boolean isGenderInfoCorrect = checkUserGenderInfo(user.getGender());
        if (!isGenderInfoCorrect)
            throw new Exception("Gender can only be M/F");
        if (user.getAge()<18)
            throw new Exception("Age must be greater or equal to 18.");
    }

    private boolean checkUserGenderInfo(char gender) {
        return gender == 'M' || gender == 'F';
    }
}
