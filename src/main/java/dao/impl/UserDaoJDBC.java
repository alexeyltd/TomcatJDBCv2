package dao.impl;

import dao.UserDAO;
import dao.executor.Executor;
import dao.impl.executorImpl.*;
import model.User;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by alexeypavlenko on 21/04/2017.
 */
public class UserDaoJDBC implements UserDAO {

    private Executor executor;
    private Connection connection;

    public UserDaoJDBC(Connection connection) {
        this.executor = new ExecutorImpl(connection);
        this.connection = connection;
    }

    public User get(long id) throws SQLException {
        return executor.execQuery("select * from data where id=" + id, result -> {
            result.next();
            return new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4));
        });
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from data where name ='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(String name, String login, String password) throws SQLException {
        executor.execUpdate("insert into data (name, login, password) " +
                "values ('" + name + "',  '" + login + "' , '" + password + "')");
    }


    public void updateUserById(long id, String name, String login, String password) throws SQLException {
        executor.execUpdate("update data SET name ='" + name + "', " +
                " login ='" + login + "' , password ='" + password + "'  where id=" + id);

    }


    public void deleteUserById(long id) throws SQLException {
        executor.execUpdate("delete from data where id =" + id);
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists data (id bigint auto_increment, name varchar(256), " +
                "login varchar(256), password varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table data");
    }


    @Override
    public void addUser(String name, String login, String password) throws UserDaoJDBCException {
        try {
            connection.setAutoCommit(false);
            createTable();
            insertUser(name, login, password);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new UserDaoJDBCException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public long updateUser(long id, String name, String login, String password) throws UserDaoJDBCException {
        try {
            connection.setAutoCommit(false);
            updateUserById(id, name, login, password);
            connection.commit();
            return getUserId(name);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new UserDaoJDBCException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public void deleteUser(long id) throws UserDaoJDBCException {
        try {
            connection.setAutoCommit(false);
            deleteUserById(id);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new UserDaoJDBCException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
}
