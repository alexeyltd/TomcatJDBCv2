package services.userServiceImpl;

import dao.UserDAO;
import dao.impl.UserDaoFactory;
import services.userService.UserService;

/**
 * Created by alexeypavlenko on 23/04/2017.
 */
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private static UserServiceImpl instance;

    public static UserServiceImpl getInstance() {

        if (instance == null) {
            instance = new UserServiceImpl();
        }

        return instance;
    }


    private UserServiceImpl() {
        UserDaoFactory userDaoFactory = UserDaoFactory.getInstance();
        this.userDAO = userDaoFactory.getUserDAO("hibernate");
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }


}
