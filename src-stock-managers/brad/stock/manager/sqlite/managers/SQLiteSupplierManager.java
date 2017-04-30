package brad.stock.manager.sqlite.managers;

import brad.stock.manager.sqlite.SQLiteCRUDOperations;
import brad.stock.sdk.data.beans.Supplier;
import brad.stock.sdk.data.managers.SupplierManager;
import brad.util.sys.BRADException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteSupplierManager implements SupplierManager {

    private SQLiteCRUDOperations crudOperations;

    public SQLiteSupplierManager(SQLiteCRUDOperations operations) {
        super();

        this.crudOperations = operations;
    }

    @Override
    public long create(Supplier supplier) throws BRADException {
        final String SQL = "INSERT INTO Suppliers (CompanyName, Code, BusinessPhone, Mobile, Fax, PostalCode," +
                " Email, WebPage, Address, Street, City, Region, Country, Notes,  Active) VALUES (" +
                "'" + supplier.getName()            + "', " +
                "'" + supplier.getCode()            + "', " +
                "'" + supplier.getBusinessPhone()   + "', " +
                "'" + supplier.getMobile()          + "', " +
                "'" + supplier.getFax()             + "', " +
                "'" + supplier.getPostalCode()      + "', " +
                "'" + supplier.getEmail()           + "', " +
                "'" + supplier.getWebPage()         + "', " +
                "'" + supplier.getAddress()         + "', " +
                "'" + supplier.getStreet()          + "', " +
                "'" + supplier.getCity()            + "', " +
                "'" + supplier.getRegion()          + "', " +
                "'" + supplier.getCountry()         + "', " +
                "'" + supplier.getNotes()           + "', " +
                " " + (supplier.isActive() ? 1 : 0) + ")";
        try {
            return crudOperations.create(SQL);
        } catch (SQLException ex) {
            throw new BRADException("Error Creating new Supplier on [Suppliers]", ex);
        }
    }

    @Override
    public Supplier fetch(long id) throws BRADException {
        Supplier supplier = null;
        try {
            ResultSet rs = crudOperations.read("SELECT * FROM Suppliers WHERE ID = " + id);
            if (rs.next()) {
                supplier = new Supplier();
                supplier.setId(rs.getLong("ID"));
                supplier.setName(rs.getString("CompanyName"));
                supplier.setCode(rs.getString("Code"));
                supplier.setBusinessPhone(rs.getString("BusinessPhone"));
                supplier.setMobile(rs.getString("Mobile"));
                supplier.setFax(rs.getString("Fax"));
                supplier.setPostalCode(rs.getString("PostalCode"));
                supplier.setEmail(rs.getString("Email"));
                supplier.setWebPage(rs.getString("WebPage"));
                supplier.setAddress(rs.getString("Address"));
                supplier.setStreet(rs.getString("Street"));
                supplier.setCity(rs.getString("City"));
                supplier.setRegion(rs.getString("Region"));
                supplier.setCountry(rs.getString("Country"));
                supplier.setNotes(rs.getString("Notes"));
                supplier.setActive(rs.getBoolean("Active"));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new BRADException("Error fetching Supplier FROM [Suppliers] WHERE [Suppliers].[ID] = " + id, ex);
        }
        return supplier;
    }

    @Override
    public List<Supplier> fetchAll(boolean active) throws BRADException {
        final List<Supplier> supplierList = new ArrayList<>();

        try {
            ResultSet rs = crudOperations.read("SELECT * FROM Suppliers" + (active ? " WHERE Active = 1": ""));
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getLong("ID"));
                supplier.setName(rs.getString("CompanyName"));
                supplier.setCode(rs.getString("Code"));
                supplier.setBusinessPhone(rs.getString("BusinessPhone"));
                supplier.setMobile(rs.getString("Mobile"));
                supplier.setFax(rs.getString("Fax"));
                supplier.setPostalCode(rs.getString("PostalCode"));
                supplier.setEmail(rs.getString("Email"));
                supplier.setWebPage(rs.getString("WebPage"));
                supplier.setAddress(rs.getString("Address"));
                supplier.setStreet(rs.getString("Street"));
                supplier.setCity(rs.getString("City"));
                supplier.setRegion(rs.getString("Region"));
                supplier.setCountry(rs.getString("Country"));
                supplier.setNotes(rs.getString("Notes"));
                supplier.setActive(rs.getBoolean("Active"));

                supplierList.add(supplier);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new BRADException("Error fetching Supplier List FROM [Suppliers]"
                    + (active ? " WHERE [Suppliers].[Active] = true": ""), ex);
        }

        return supplierList;
    }

    @Override
    public void update(Supplier supplier) throws BRADException {
        final String SQL = "UPDATE Suppliers SET " +
                "CompanyName = '"     + supplier.getName()                + "', " +
                "Code = '"            + supplier.getCode()                + "', " +
                "BusinessPhone ='"    + supplier.getBusinessPhone()       + "', " +
                "Mobile = '"          + supplier.getMobile()              + "', " +
                "Fax = '"             + supplier.getFax()                 + "', " +
                "PostalCode = '"      + supplier.getPostalCode()          + "', " +
                "Email = '"           + supplier.getEmail()               + "', " +
                "WebPage = '"         + supplier.getWebPage()             + "', " +
                "Address ='"          + supplier.getAddress()             + "', " +
                "Street ='"           + supplier.getStreet()              + "', " +
                "City ='"             + supplier.getCity()                + "', " +
                "Region = '"          + supplier.getRegion()              + "', " +
                "Country ='"          + supplier.getCountry()             + "', " +
                "Notes = '"           + supplier.getNotes()               + "', " +
                "Active = "           + (supplier.isActive() ? "1" : "0") + " "   +
                "WHERE ID = "         + supplier.getId();
        try {
            crudOperations.update(SQL);
        } catch (SQLException ex) {
            throw new BRADException("Error updating [Suppliers] WHERE [Suppliers].[ID] = " + supplier.getId(), ex);
        }
    }

    @Override
    public void delete(Supplier supplier) throws BRADException {
        try {
            crudOperations.delete("DELETE FROM Suppliers WHERE ID = " + supplier.getId());
        } catch (SQLException ex) {
            throw new BRADException("Error Deleting from [Suppliers] WHERE [Suppliers].[ID] = " + supplier.getId());
        }
    }
}
