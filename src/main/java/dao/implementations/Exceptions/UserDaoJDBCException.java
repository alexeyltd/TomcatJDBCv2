package dao.implementations.Exceptions;

import java.sql.SQLException;


public class UserDaoJDBCException extends Throwable {

    public UserDaoJDBCException(Throwable throwable) {
        super(throwable);
    }

}
