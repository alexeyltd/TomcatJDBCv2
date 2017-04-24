package dao.impl;

import dao.UserDAO;
import dao.impl.dbHelper.*;

/**
 * Created by alexeypavlenko on 23/04/2017.
 */
public class UserDaoFactory {

    public DBHelper dbHelper = DBHelper.getInstance();

    private static UserDaoFactory instance;

    public static UserDaoFactory getInstance() {

        if (instance == null) {
            instance = new UserDaoFactory();
        }

        return instance;
    }


    private UserDaoFactory() {

    }


    public UserDAO getUserDAO(String type) {
        if (type.equalsIgnoreCase("jdbc")) {
            return new UserDaoJDBC(dbHelper.getConnection());
        } else if (type.equalsIgnoreCase("hibernate")) {
            return  new UserDaoHib(dbHelper.getConfiguration());
        }
        else {
            return null;
        }
    }


}
