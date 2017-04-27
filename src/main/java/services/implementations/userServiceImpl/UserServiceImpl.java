package services.implementations.userServiceImpl;

import dao.abstracts.UserDao.UserDAO;
import dao.implementations.UserDao.UserDaoFactory;
import dao.implementations.Exceptions.UserDaoHibException;
import dao.implementations.Exceptions.UserDaoJDBCException;
import model.User;
import services.implementations.propertiesHelper.PropertiesHelper;
import services.abstracts.userService.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private static UserServiceImpl instance;

    public static UserServiceImpl getInstance() {

        if (instance == null) {
            instance = new UserServiceImpl();
        }

        return instance;
    }

//    private String type = System.getenv("type");

    private UserServiceImpl() {
        UserDaoFactory userDaoFactory = UserDaoFactory.getInstance();
        PropertiesHelper propertiesHelper = PropertiesHelper.getInstance();
        this.userDAO = userDaoFactory.getUserDAO(propertiesHelper.getPropertiesType());
    }

    @Override
    public void addUser(String name, String login, String password, String role) throws UserDaoJDBCException, UserDaoHibException {
        userDAO.addUser(name, login, password, role);
    }

    @Override
    public void updateUser(long id, String name, String login, String password, String role) throws UserDaoJDBCException, UserDaoHibException {
        userDAO.updateUser(id, name, login, password, role);
    }

    @Override
    public void deleteUser(long id) throws UserDaoJDBCException, UserDaoHibException {
        userDAO.deleteUser(id);

    }

    @Override
    public List<User> getUsers() throws UserDaoJDBCException, UserDaoHibException {
        return userDAO.getUsers();
    }

    @Override
    public User getOneUser(String login, String password) {
        return userDAO.getOneUser(login, password);
    }
}
