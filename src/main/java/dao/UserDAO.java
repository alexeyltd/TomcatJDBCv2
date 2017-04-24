package dao;

import dao.impl.UserDaoHibException;
import dao.impl.UserDaoJDBCException;

/**
 * Created by alexeypavlenko on 21/04/2017.
 */
public interface UserDAO {

    public void addUser(String name, String login, String password) throws UserDaoJDBCException, UserDaoHibException;

    public long updateUser(long id, String name, String login, String password) throws UserDaoJDBCException, UserDaoHibException;

    public void deleteUser(long id) throws UserDaoJDBCException, UserDaoHibException;

}
