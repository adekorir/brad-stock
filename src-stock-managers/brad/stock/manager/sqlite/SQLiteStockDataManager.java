package brad.stock.manager.sqlite;

import brad.stock.manager.sqlite.managers.SQLiteProductManager;
import brad.stock.manager.sqlite.managers.SQLiteSupplierManager;
import brad.stock.sdk.data.StockDataManager;
import brad.stock.sdk.data.managers.ProductManager;
import brad.stock.sdk.data.managers.SupplierManager;
import brad.util.sys.BRADException;
import brad.util.sys.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteStockDataManager implements StockDataManager, Database {

    private SQLiteCRUDOperations crudOperations;
    private Connection connection;

    @Override
    public String getName() {
        return "SQLite";
    }

    @Override
    public void open(String url, String username, String password) throws BRADException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            throw new BRADException("Error finding SQLite Driver");
        }

        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
        } catch (SQLException ex) {
            throw new BRADException("Error registering SQLite database", ex);
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + url);
        } catch (SQLException ex) {
            throw new BRADException("Error opening SQLite Database", ex);
        }

        try {
            crudOperations = new SQLiteCRUDOperations(connection);
            crudOperations.initialize();
        } catch (SQLException ex) {
            throw new BRADException("Error initializing database", ex);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }

    @Override
    public SupplierManager getSupplierManager() {
        return new SQLiteSupplierManager(crudOperations);
    }

    @Override
    public ProductManager getProductManager() {
        return new SQLiteProductManager(crudOperations);
    }

    @Override
    public void close() throws BRADException {
        try {
            if (connection != null) {
                if (!connection.isClosed())
                    connection.close();
                connection = null;
            }
        } catch (SQLException ex) {
            throw new BRADException("Error Closing SQLite Connection", ex);
        }
    }
}
