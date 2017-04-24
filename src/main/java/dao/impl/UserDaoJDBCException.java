package dao.impl;

import java.sql.SQLException;

/**
 * Created by alexeypavlenko on 24/04/2017.
 */
public class UserDaoJDBCException extends Throwable {

    public UserDaoJDBCException(Throwable throwable) {
        super(throwable);
    }

}
