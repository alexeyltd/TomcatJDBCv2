package dao.executor;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexeypavlenko on 21/04/2017.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}

