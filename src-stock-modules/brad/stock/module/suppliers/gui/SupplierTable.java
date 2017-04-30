package brad.stock.module.suppliers.gui;

import brad.stock.sdk.data.beans.Supplier;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class SupplierTable extends TableView<Supplier> {

    private TableColumn<Supplier, Long> colId;
    private TableColumn<Supplier, String> colName, colBusinessPhone, colMobile, colFax, colEmail, colWebPage;
    private TableColumn<Supplier, String> colAddress, colPostalCode, colStreet, colCity, colRegion, colCountry, colNotes;
    private TableColumn<Supplier, Boolean> colActive;

    {
        colId = new TableColumn<>("ID");
        colName = new TableColumn<>("Company Name");
        colBusinessPhone = new TableColumn<>("Business Phone");
        colMobile = new TableColumn<>("Mobile");
        colFax = new TableColumn<>("Fax");
        colEmail = new TableColumn<>("Email");
        colWebPage = new TableColumn<>("Web Page");
        colAddress = new TableColumn<>("Address");
        colPostalCode = new TableColumn<>("Postal Code");
        colStreet = new TableColumn<>("Street");
        colCity = new TableColumn<>("City");
        colRegion = new TableColumn<>("Region");
        colCountry = new TableColumn<>("Country");
        colNotes = new TableColumn<>("Notes");
        colActive = new TableColumn<>("Active");
    }

    public SupplierTable() {
        super();

        build();
    }

    private void build() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        getColumns().add(colId);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        getColumns().add(colName);

        colBusinessPhone.setCellValueFactory(new PropertyValueFactory<>("businessPhone"));
        getColumns().add(colBusinessPhone);

        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        getColumns().add(colMobile);

        colFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
        getColumns().add(colFax);

        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        getColumns().add(colEmail);

        colWebPage.setCellValueFactory(new PropertyValueFactory<>("webPage"));
        getColumns().add(colWebPage);

        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        getColumns().add(colAddress);

        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        getColumns().add(colPostalCode);

        colStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        getColumns().add(colStreet);

        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        getColumns().add(colCity);

        colRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
        getColumns().add(colRegion);

        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        getColumns().add(colCountry);

        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        getColumns().add(colNotes);

        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        colActive.setCellFactory(val -> new CheckBoxTableCell<>());
        getColumns().add(colActive);
    }
}
