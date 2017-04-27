package dao.abstracts.Executor;

import java.sql.SQLException;


public interface Executor {


    void execUpdate(String update) throws SQLException;

    <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException;


}
