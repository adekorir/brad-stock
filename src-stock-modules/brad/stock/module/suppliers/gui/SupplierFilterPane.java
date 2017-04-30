package brad.stock.module.suppliers.gui;

import brad.stock.sdk.data.beans.Supplier;
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

import java.util.List;

public class SupplierFilterPane extends BorderPane {

    private SupplierTable table;
    private Label lblRecCount, lblFilterCount;
    private ObservableList<Supplier> masterData;
    private TextField filterField;
    {
        filterField = new TextField();
        table = new SupplierTable();
        lblRecCount = new Label("0");
        lblFilterCount = new Label("0");
        masterData = FXCollections.observableArrayList();
    }

    public SupplierFilterPane() {
        super();

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Supplier> filteredData = new FilteredList<>(masterData, p -> true);

        HBox.setHgrow(filterField, Priority.ALWAYS);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(supplier -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase().trim();

                try {
                    return supplier.getId() == Long.parseLong(lowerCaseFilter);
                } catch (NumberFormatException ignore) {
                    return (supplier.getName().toLowerCase().contains(lowerCaseFilter)); // Filter matches company name.
                    // Does not match.
                }
            });
            // update filtered data count
            lblFilterCount.setText(table.getItems().size() + "");
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Supplier> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);

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

    public void refresh(List<Supplier> supplierList) {
        masterData.clear();
        filterField.clear();
        masterData.addAll(supplierList);
        lblRecCount.setText("" + masterData.size());
        lblFilterCount.setText("" + table.getItems().size());
    }

    public SupplierTable getTable() {
        return table;
    }
}
