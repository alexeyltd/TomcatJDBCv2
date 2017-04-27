package dao.implementations.UserDao;

import dao.abstracts.UserDao.UserDAO;
import dao.abstracts.Executor.Executor;
import dao.implementations.Exceptions.UserDaoJDBCException;
import dao.implementations.Executor.ExecutorImpl;
import model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements UserDAO {

    private Executor executor;
    private Connection connection;

    public UserDaoJDBC(Connection connection) {
        this.executor = new ExecutorImpl(connection);
        this.connection = connection;
    }

    @Override
    public User getOneUser(String login, String password) {

        try {
            return executor.execQuery("select * from data where id=" + getUserId(login, password), result -> {
                    result.next();
                    return new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    private long getUserId(String login, String password) {

//        and password='" + password + "'

        try {
            return executor.execQuery("select * from data where login='" + login + "'", result -> {
                result.next();
                return result.getLong(1);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void insertUser(String name, String login, String password, String role) throws SQLException {
        executor.execUpdate("insert into data (name, login, password, role) " +
                "values ('" + name + "',  '" + login + "' , '" + password + "', '" + role + "')");
    }


    public void updateUserById(long id, String name, String login, String password, String role) throws SQLException {
        executor.execUpdate("update data SET name ='" + name + "', " +
                " login ='" + login + "' , password ='" + password + "', role = '" + role + "'  where id=" + id);

    }


    public void deleteUserById(long id) throws SQLException {
        executor.execUpdate("delete from data where id =" + id);
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists data (id bigint auto_increment, name varchar(256), " +
                "login varchar(256), password varchar(256), role varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table data");
    }


    @Override
    public void addUser(String name, String login, String password, String role) throws UserDaoJDBCException {
        try {
            connection.setAutoCommit(false);
            createTable();
            insertUser(name, login, password, role);
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
    public void updateUser(long id, String name, String login, String password, String role) throws UserDaoJDBCException {
        try {
            connection.setAutoCommit(false);
            updateUserById(id, name, login, password, role);
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

    @Override
    public List<User> getUsers() {

        List<User> list = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from data");

            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String login = resultSet.getString(3);
                String password = resultSet.getString(4);
                String role = resultSet.getString(5);

                list.add(new User(id, name, login, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
