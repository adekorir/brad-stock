package brad.stock.manager.sqlite.managers;

import brad.stock.manager.sqlite.SQLiteCRUDOperations;
import brad.stock.sdk.data.beans.Product;
import brad.stock.sdk.data.managers.ProductManager;
import brad.util.data.bean.SubCategory;
import brad.util.sys.BRADException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteProductManager implements ProductManager {

    private SQLiteCRUDOperations crudOperations;

    public SQLiteProductManager(SQLiteCRUDOperations crudOperations) {
        super();

        this.crudOperations = crudOperations;
    }

    @Override
    public long create(Product product) throws BRADException {
        long id;
        final String SQL = "INSERT INTO Products ("
                + "CategoryID, Description, Notes, PurchasePrice, SalePrice, ReorderLimit, ReorderAmount, Discontinued"
                + ") VALUES ("

                + ""    + product.getSubCategory().getId()             + ", "
                + "'"   + product.getDescription()                  + "', "
                + "'"   + product.getNotes()                        + "', "
                + ""    + product.getPurchasePrice()                + ", "
                + ""    + product.getSalePrice()                    + ", "
                + ""    + product.getReorderLimit()                 + ", "
                + ""    + product.getReorderAmount()                + ", "
                + ""    + (product.isDiscontinued() ? "1" : "0")    + ")";
        try {
            id = crudOperations.create(SQL);
        } catch (SQLException ex) {
            throw new BRADException("Error Creating new Product on [Products]", ex);
        }
        return id;
    }

    @Override
    public Product fetch(long id) throws BRADException {
        Product product = null;

        final String SQL = "SELECT p.*, c.CategoryName, c.Notes AS CategoryNotes "
                + "FROM Products p INNER JOIN Categories c ON (p.CategoryID == c.ID) "
                + "WHERE p.ID = " + id;
        try {
            ResultSet rs = crudOperations.read(SQL);
            if (rs.next()) {
                product = new Product();
                product.setId(id);

                SubCategory subCategory = new SubCategory();
                subCategory.setId(rs.getLong("CategoryID"));
                subCategory.setTitle(rs.getString("CategoryName"));
                subCategory.setNotes(rs.getString("CategoryNotes"));
                product.setSubCategory(subCategory);

                product.setDescription(rs.getString("Description"));
                product.setNotes(rs.getString("Notes"));
                product.setPurchasePrice(rs.getDouble("PurchasePrice"));
                product.setSalePrice(rs.getDouble("SalePrice"));
                product.setReorderLimit(rs.getDouble("ReorderLimit"));
                product.setReorderAmount(rs.getDouble("ReorderAmount"));
                product.setDiscontinued(rs.getBoolean("Discontinued"));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new BRADException("Error Fetching Product from [Products] where [Products].[ID] = " + id, ex);
        }

        return product;
    }

    @Override
    public List<Product> fetchAll(boolean discontinued) throws BRADException {
        final List<Product> productList = new ArrayList<>();

        final String SQL = "SELECT p.*, c.CategoryName, c.Notes AS CategoryNotes "
                + "FROM Products p INNER JOIN Categories c ON (p.CategoryID == c.ID) "
                + (discontinued ? "" : " WHERE Discontinued = 0");
        try {
            ResultSet rs = crudOperations.read(SQL);
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("ID"));

                SubCategory subCategory = new SubCategory();
                subCategory.setId(rs.getLong("CategoryID"));
                subCategory.setTitle(rs.getString("CategoryName"));
                subCategory.setNotes(rs.getString("CategoryNotes"));
                product.setSubCategory(subCategory);

                product.setDescription(rs.getString("Description"));
                product.setNotes(rs.getString("Notes"));
                product.setPurchasePrice(rs.getDouble("PurchasePrice"));
                product.setSalePrice(rs.getDouble("SalePrice"));
                product.setReorderLimit(rs.getDouble("ReorderLimit"));
                product.setReorderAmount(rs.getDouble("ReorderAmount"));
                product.setDiscontinued(rs.getBoolean("Discontinued"));

                productList.add(product);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new BRADException("Error Fetching Products List FROM [Products]"
                    + (discontinued ? "" : " WHERE Discontinued = false"), ex);
        }
        return productList;
    }

    @Override
    public void update(Product product) throws BRADException {
        final String SQL = "UPDATE Products SET "
                + "CategoryID = " + product.getSubCategory().getId() + ", "
                + "Description = '" + product.getDescription() + "', "
                + "Notes = '" + product.getNotes() + "', "
                + "PurchasePrice = " + product.getPurchasePrice() + ", "
                + "ReorderLimit = " + product.getReorderLimit() + ", "
                + "ReorderAmount = " + product.getReorderAmount() + ", "
                + "Discontinued = " + (product.isDiscontinued() ? "1" : "0") + " "
                + "WHERE ID = " + product.getId() ;
        try {
            crudOperations.update(SQL);
        } catch (SQLException ex) {
            throw new BRADException("Error updating [Products] WHERE [Products].[ID] = " + product.getId());
        }
    }

    @Override
    public void discontinueProduct(long id) throws BRADException {
        try {
            crudOperations.update("UPDATE Products SET Discontinued = 1 WHERE ID = " + id);
        } catch (SQLException ex) {
            throw new BRADException("Error Updating [Products].[Discontinued] = true WHERE ID = " + id, ex);
        }
    }

    @Override
    public void delete(Product product) throws BRADException {
        try {
            crudOperations.delete("DELETE FROM Products WHERE ID = " + product.getId());
        } catch (SQLException ex) {
            throw new BRADException("Error Deleting FROM [Products] WHERE [Products].[ID] = " + product.getId(), ex);
        }
    }
}
