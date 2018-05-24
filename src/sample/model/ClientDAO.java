package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DbHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {
    private static class ClientContract {
        private static final String TABLE_NAME = "CUSTOMER";
        private static final String COLUMN_NAME_ID = "ID_CUSTOMER";
        private static final String COLUMN_NAME_NAME = "NAME";
        private static final String COLUMN_NAME_SURNAME = "SURNAME";
        private static final String COLUMN_NAME_TELEPHONE_NUMBER = "TELEPHONE_NUMBER";
        private static final String COLUMN_NAME_EMAIL = "EMAIL";
    }

    /**
     * Method creates object for every row in CLIENTS table.
     * @param resultSet result of SELECT query. Collection of objects which contain client values.
     * @return Collection of objects representing every row in CLIENTS table
     * @throws SQLException thrown when sql error appears
     */
    private static ObservableList<Client> getClientList(ResultSet resultSet) throws SQLException {
        ObservableList<Client> clientList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            Client client = new Client();
            client.setId(resultSet.getInt(ClientContract.COLUMN_NAME_ID));
            client.setName(resultSet.getString(ClientContract.COLUMN_NAME_NAME));
            client.setSurname(resultSet.getString(ClientContract.COLUMN_NAME_SURNAME));
            client.setEmail(resultSet.getString(ClientContract.COLUMN_NAME_EMAIL));
            client.setTelephoneNumber(resultSet.getString(ClientContract.COLUMN_NAME_TELEPHONE_NUMBER));

            clientList.add(client);
        }

        return clientList;
    }

    /**
     * Method fetches all clients data from database.
     * @return Collection of objects representing particular rows in database
     * @throws SQLException thrown when sql error appears
     * @throws ClassNotFoundException thrown when JDBC Driver is not found
     */
    public static ObservableList<Client> getClients() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM " + ClientContract.TABLE_NAME + " ORDER BY " + ClientContract.COLUMN_NAME_ID + " DESC";
        return getClientList(DbHelper.executeQuery(sqlQuery));
    }

    /**
     * Method executes INSERT query of provided client represented by Client class object.
     * @param client client represented by Client class object
     */
    public static void insertClient(Client client) throws SQLException, ClassNotFoundException {
        if(client == null) return;

        String sqlQuery = String.format("INSERT INTO %s VALUES (0, '%s', '%s', %d, '%s')", ClientContract.TABLE_NAME,
                 client.getName(), client.getSurname(), Long.parseLong(client.getTelephoneNumber()), client.getEmail());

        DbHelper.executeUpdateQuery(sqlQuery);
    }

    /**
     * Method executes UPDATE query on row in CLIENTS database which is represented by provided Client class object.
     * @param client object which represents Client in database.
     *               Row in database will be updated to contain all values that provided object possess.
     * @throws SQLException thrown when sql error appears
     * @throws ClassNotFoundException thrown when JDBC Driver is not found
     */
    public static void updateClient(Client client) throws SQLException, ClassNotFoundException {
        if(client == null) return;

        String sqlQuery = String.format("UPDATE %s SET %s = '%s', %s = '%s', %s = '%s', %s = %d WHERE %s = %d", ClientContract.TABLE_NAME,
                ClientContract.COLUMN_NAME_NAME, client.getName(), ClientContract.COLUMN_NAME_SURNAME, client.getSurname(),
                ClientContract.COLUMN_NAME_EMAIL, client.getEmail(), ClientContract.COLUMN_NAME_TELEPHONE_NUMBER,
                Long.parseLong(client.getTelephoneNumber()), ClientContract.COLUMN_NAME_ID, client.getId());

        DbHelper.executeUpdateQuery(sqlQuery);
    }

    /**
     * Method executes client DELETE query on database.
     * @param id id of client which is to be deleted from database
     * @throws SQLException thrown when sql error appears
     * @throws ClassNotFoundException thrown when JDBC Driver is not found
     */
    public static void deleteClient(int id) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s = %d", ClientContract.TABLE_NAME,
                ClientContract.COLUMN_NAME_ID, id);

        DbHelper.executeUpdateQuery(sqlQuery);
    }
}
