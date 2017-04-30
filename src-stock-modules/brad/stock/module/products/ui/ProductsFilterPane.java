package brad.stock.module.products.ui;

import brad.stock.sdk.data.beans.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class ProductsFilterPane extends BorderPane {

    private TextField filterField;
    private ProductsTable table;
    private Label lblFilterCount, lblRecCount;
    private ObservableList<Product> masterData;

    {
        filterField = new TextField();
        table = new ProductsTable();
        lblFilterCount = new Label("0");
        lblRecCount = new Label("");
        masterData = FXCollections.observableArrayList();
    }

    public ProductsFilterPane() {
        super();

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Product> filteredData = new FilteredList<>(masterData, p -> true);

        HBox.setHgrow(filterField, Priority.ALWAYS);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase().trim();

                try {
                    return product.getId() == Long.parseLong(lowerCaseFilter);
                } catch (NumberFormatException ignore) {
                    return (product.getDescription().toLowerCase().contains(lowerCaseFilter)); // Filter matches company name.
                    // Does not match.
                }
            });
            // update filtered data count
            lblFilterCount.setText(table.getItems().size() + "");
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Product> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);

        filterField.setPromptText("Filter by ");
        filterField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE)
                filterField.clear();
        });

        final HBox filterPane = new HBox(filterField);
        filterPane.setPadding(new Insets(0, 0, 10, 0));

        Region hSpacer = new Region();
        HBox.setHgrow(hSpacer, Priority.ALWAYS);

        final HBox statusPane = new HBox(10, new Label("Filtered"), lblFilterCount , new Label("of"),
                lblRecCount, new Label("Record(s)"), hSpacer);
        statusPane.setPadding(new Insets(10, 0, 0, 0));

        setCenter(table);
        setTop(filterPane);
        setBottom(statusPane);
    }
}
