package brad.stock.manager.sqlite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteCRUDOperations {

    private Connection connection;

    SQLiteCRUDOperations(Connection connection) {
        super();

        this.connection = connection;
    }

    void initialize() throws SQLException {
        final Statement statement = connection.createStatement();

        final String SUPPLIERS = "CREATE TABLE IF NOT EXISTS Suppliers (\n"
                + "ID INTEGER NOT NULL,\n"
                + "CompanyName TEXT NOT NULL,\n"
                + "Code TEXT, \n"
                + "BusinessPhone TEXT NOT NULL,\n"
                + "Mobile TEXT NOT NULL,\n"
                + "Fax TEXT,\n"
                + "Email TEXT,\n"
                + "WebPage TEXT,\n"
                + "Address TEXT,\n"
                + "PostalCode TEXT,\n"
                + "Street TEXT,\n"
                + "City TEXT,\n"
                + "Region TEXT,\n"
                + "Country TEXT,\n"
                + "Notes TEXT,\n"
                + "Active BOOLEAN DEFAULT 1,\n\n"

                + "PRIMARY KEY (ID))";
        statement.addBatch(SUPPLIERS);

        final String CATEGORIES = "CREATE TABLE IF NOT EXISTS Categories ("
                + "ID INTEGER NOT NULL, \n"
                + "CategoryName TEXT NOT NULL, \n"
                + "Notes TEXT, \n\n"

                + "PRIMARY KEY (ID))";
        statement.addBatch(CATEGORIES);


        final String SUB_CATEGORIES = "CREATE TABLE IF NOT EXISTS SubCategories ("
                + "ID INTEGER NOT NULL, \n"
                + "CategoryID INTEGER NOT NULL, \n"
                + "SubCategoryName TEXT NOT NULL, \n"
                + "Notes TEXT, \n\n"

                + "FOREIGN KEY (CategoryID) REFERENCES Categories (ID), \n\n"

                + "PRIMARY KEY (ID))";
        statement.addBatch(SUB_CATEGORIES);

        final String PRODUCTS = "CREATE TABLE IF NOT EXISTS Products (\n"
                + "ID INTEGER NOT NULL, \n"
                + "CategoryID INTEGER NOT NULL, \n"
                + "Description TEXT NOT NULL, \n"
                + "Notes TEXT, \n"
                + "PurchasePrice DOUBLE, \n"
                + "QuotePrice DOUBLE , \n"
                + "SalePrice DOUBLE, \n"
                + "ReorderLimit DOUBLE, \n"
                + "ReorderAmount DOUBLE, \n"
                + "Discontinued BOOLEAN DEFAULT 1, \n\n"

                + "FOREIGN KEY (CategoryID) REFERENCES SubCategories (ID),\n\n"

                + "PRIMARY KEY (ID))";
        statement.addBatch(PRODUCTS);

        statement.executeBatch();
    }

    public long create(String sql) throws SQLException {
        long generatedId = 0;

        connection.setAutoCommit(false);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

            ResultSet rs = statement.executeQuery("SELECT last_insert_rowid()");

            if (rs.next())
                generatedId = rs.getLong(1);
            rs.close();

            statement.close();
            connection.commit();
            connection.setAutoCommit(true);

            return generatedId;
        } catch (SQLException ex) {
            throw new SQLException("Error creating generated id");
        }
    }

    public ResultSet read(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }

    public void update(String sql) throws SQLException {
        connection.createStatement().executeUpdate(sql);
    }

    public void delete(String sql) throws SQLException {
        connection.createStatement().execute(sql);
    }
}
