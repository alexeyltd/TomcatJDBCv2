package services.abstracts.userService;

import dao.implementations.Exceptions.UserDaoHibException;
import dao.implementations.Exceptions.UserDaoJDBCException;
import model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by alexeypavlenko on 23/04/2017.
 */
public interface UserService {

     void addUser(String name, String login, String password, String role) throws UserDaoJDBCException, UserDaoHibException;

     void updateUser(long id, String name, String login, String password, String role) throws UserDaoJDBCException, UserDaoHibException;

     void deleteUser(long id) throws UserDaoJDBCException, UserDaoHibException;

     List<User> getUsers() throws UserDaoJDBCException, UserDaoHibException;

     User getOneUser(String login, String password);

}
