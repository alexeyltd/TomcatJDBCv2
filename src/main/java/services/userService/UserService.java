package services.userService;

import dao.UserDAO;
import services.userServiceImpl.UserServiceImpl;

/**
 * Created by alexeypavlenko on 23/04/2017.
 */
public interface UserService {

    public UserDAO getUserDAO();

}
