package brad.stock.module.products.ui;

import brad.stock.sdk.data.beans.Product;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductsTable extends TableView<Product> {

    private TableColumn<Product, Long> colID;
    private TableColumn<Product, String> colDescription;
    private TableColumn<Product, Double> colSalePrice, colPurchasePrice, colReorderLimit, colReorderAmount;
    private TableColumn<Product, Boolean> colDiscontinued;

    {
        colID = new TableColumn<>("ID");
        colDescription = new TableColumn<>("Description");
        colSalePrice = new TableColumn<>("Sale Price");
        colPurchasePrice = new TableColumn<>("Purchase Price");
        colReorderLimit = new TableColumn<>("Reorder Limit");
        colReorderAmount = new TableColumn<>("Reorder Amount");
        colDiscontinued = new TableColumn<>("Discontinued");
    }

    public ProductsTable() {
        super();

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        getColumns().add(colID);

        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        getColumns().add(colDescription);

        colSalePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        getColumns().add(colSalePrice);

        colPurchasePrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        getColumns().add(colPurchasePrice);

        colReorderLimit.setCellValueFactory(new PropertyValueFactory<>("reorderLimit"));
        getColumns().add(colReorderLimit);

        colReorderAmount.setCellValueFactory(new PropertyValueFactory<>("reorderAmount"));
        getColumns().add(colReorderAmount);

        colDiscontinued.setCellValueFactory(new PropertyValueFactory<>("discontinued"));
        colDiscontinued.setCellFactory(val -> new CheckBoxTableCell<>());
        getColumns().add(colDiscontinued);
    }
}
