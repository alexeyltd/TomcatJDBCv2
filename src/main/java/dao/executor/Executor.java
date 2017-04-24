package dao.executor;

import java.sql.SQLException;

/**
 * Created by alexeypavlenko on 21/04/2017.
 */
public interface Executor {


    public void execUpdate(String update) throws SQLException;

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException;


}
