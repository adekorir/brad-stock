package brad.stock.manager.derby;

import brad.stock.manager.derby.managers.DerbyProductManager;
import brad.stock.manager.derby.managers.DerbySupplierManager;
import brad.stock.sdk.data.StockDataManager;
import brad.stock.sdk.data.managers.ProductManager;
import brad.stock.sdk.data.managers.SupplierManager;
import brad.util.sys.BRADException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyStockDataManager implements StockDataManager {

    private DerbyCRUDOperations crudOperations;
    private Connection connection;

    {
        connection = null;
    }

    @Override
    public String getName() {
        return "Derby";
    }

    @Override
    public void open(String url, String username, String password) throws BRADException {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.AutoloadedDriver());
        } catch (SQLException ex) {
            throw new BRADException("Error registering derby driver", ex);
        }

        try {
            connection = DriverManager.getConnection("jdbc:derby:" + url + ";create=true");
        } catch (SQLException ex) {
            throw new BRADException("Error initializing database connection", ex);
        }

        try {
            crudOperations = new DerbyCRUDOperations(connection);
            crudOperations.initialize();
        } catch (SQLException ex) {
            throw new BRADException("Error initializing Derby database", ex);
        }
    }

    @Override
    public SupplierManager getSupplierManager() {
        return new DerbySupplierManager(crudOperations);
    }

    @Override
    public ProductManager getProductManager() {
        return new DerbyProductManager(crudOperations);
    }

    @Override
    public void close() throws BRADException {
        try {
            if (connection != null) {
                if (!connection.isClosed()) {
                    connection = DriverManager.getConnection("jdbc:derby:;shutdown=true");
                    connection.close();
                }
                connection = null;
            }
        } catch (SQLException ex) {
            throw new BRADException("Error closing database connection", ex);
        }
    }
}
