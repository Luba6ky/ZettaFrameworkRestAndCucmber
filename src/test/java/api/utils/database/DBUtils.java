package api.utils.database;

import java.sql.ResultSet;

public class DBUtils {

    private DBConnection dbConnection;

    public DBUtils(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String getValueFromDB(String query, String columnName) throws Exception {
        ResultSet resultSet = dbConnection.executeQuery(query);
        String value = null;
        while (resultSet.next()) {
            value = resultSet.getString(columnName);
        }
        return value;
    }
}
