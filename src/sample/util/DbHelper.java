package sample.util;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;

public class DbHelper {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private static final String USERNAME = "wkurek";
    private static final String PASSWORD = "wkurek";

    private static Connection connection;

    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException exception) {
            System.err.println("JDBC Driver not found.");
            exception.printStackTrace();
            throw exception;
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException exception) {
            System.err.println("Cannot connect to database.");
            exception.printStackTrace();
            throw exception;
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException exception) {
            System.err.println("Cannot close connection to database.");
            exception.printStackTrace();
            throw exception;
        }
    }

    public static ResultSet executeQuery(String sqlQuery) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl cachedRowSet;

        try {
            dbConnect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);

        } catch (SQLException exception) {
            System.err.println("Error while executing SELECT query.");
            exception.printStackTrace();
            throw exception;
        } finally {
            if(resultSet != null) {
                resultSet.close();
            }

            if(statement != null) {
                statement.close();
            }

            dbDisconnect();
        }

        return cachedRowSet;
    }

    public static int executeUpdateQuery(String sqlQuery) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        int affectedRows;

        try {
            dbConnect();
            statement = connection.createStatement();
            affectedRows = statement.executeUpdate(sqlQuery);
        } catch (SQLException exception) {
            System.err.println("Error while executing UPDATE query.");
            exception.printStackTrace();
            throw exception;
        } finally {
            if(statement != null) {
                statement.close();
            }

            dbDisconnect();
        }

        return affectedRows;
    }

}
