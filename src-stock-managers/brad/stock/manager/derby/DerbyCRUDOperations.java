package brad.stock.manager.derby;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyCRUDOperations {

    private Connection connection;

    DerbyCRUDOperations(Connection connection) {
        super();

        this.connection = connection;
    }

    void initialize() throws SQLException {
        try (Statement statement = connection.createStatement()) {

            final String SUPPLIERS = "CREATE TABLE Suppliers (\n"
                    + "ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "CompanyName VARCHAR (40) NOT NULL,\n"
                    + "Code VARCHAR (25) NOT NULL,\n"
                    + "BusinessPhone VARCHAR (50) NOT NULL,\n"
                    + "Mobile VARCHAR (50) NOT NULL,\n"
                    + "Fax VARCHAR (25),\n"
                    + "Email VARCHAR (40),\n"
                    + "WebPage VARCHAR (40),\n"
                    + "Address VARCHAR (50),\n"
                    + "PostalCode VARCHAR (10),\n"
                    + "Street VARCHAR (25),\n"
                    + "City VARCHAR (25),\n"
                    + "Region VARCHAR (25),\n"
                    + "Country VARCHAR (50),\n"
                    + "Notes VARCHAR (100),\n"
                    + "Active BOOLEAN,\n\n"

                    + "PRIMARY KEY (ID))";
            statement.addBatch(SUPPLIERS);

            final String CATEGORIES = "CREATE TABLE Categories ("
                    + "ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
                    + "CategoryName VARCHAR (25) NOT NULL, \n"
                    + "Notes VARCHAR (50), \n\n"

                    + "PRIMARY KEY (ID))";
            statement.addBatch(CATEGORIES);

            final String SUB_CATEGORIES = "CREATE TABLE SubCategories ("
                    + "ID INTEGER NOT NULL, \n"
                    + "CategoryID INTEGER NOT NULL, \n"
                    + "SubCategoryName VARCHAR (25) NOT NULL, \n"
                    + "Notes VARCHAR (50), \n\n"

                    + "FOREIGN KEY (CategoryID) REFERENCES Categories (ID), \n\n"

                    + "PRIMARY KEY (ID))";
            statement.addBatch(SUB_CATEGORIES);

            final String PRODUCTS = "CREATE TABLE Products (\n"
                    + "ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
                    + "CategoryID INTEGER NOT NULL, \n"
                    + "Description VARCHAR (30) NOT NULL, \n"
                    + "Notes VARCHAR (100), \n"
                    + "PurchasePrice DOUBLE, \n"
                    + "QuotePrice DOUBLE , \n"
                    + "SalePrice DOUBLE, \n"
                    + "ReorderLimit DOUBLE, \n"
                    + "ReorderAmount DOUBLE, \n"
                    + "Discontinued BOOLEAN, \n\n"

                    + "FOREIGN KEY (CategoryID) REFERENCES SubCategories (ID),\n\n"

                    + "PRIMARY KEY (ID))";
            statement.addBatch(PRODUCTS);

            statement.executeBatch();

        } catch (SQLException ex) {
            throw new SQLException("Error initializing database", ex);
        }
    }

    public long create(String sql) throws SQLException {
        long generatedId = 0;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
                generatedId = rs.getLong(1);
            rs.close();
            return generatedId;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Error getting generated key");
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
