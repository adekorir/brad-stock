package brad.stock.module.suppliers;

import brad.stock.module.suppliers.forms.SupplierDetailsFormController;
import brad.stock.module.suppliers.gui.SupplierFilterPane;
import brad.stock.module.suppliers.gui.SupplierTable;
import brad.stock.sdk.api.DesktopPlatform;
import brad.stock.sdk.api.StockModule;
import brad.stock.sdk.data.beans.Supplier;
import brad.util.sys.BRADException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SuppliersStockModule extends StockModule {

    private Button btnNewSupplier;
    private DesktopPlatform platform;

    {
        btnNewSupplier = new Button("New Supplier");
    }

    @Override
    public String getName() {
        return "Suppliers";
    }

    @Override
    public void initialize(DesktopPlatform desktopPlatform) {
        platform = desktopPlatform;
    }

    @Override
    public void launch() {
        ToolBar toolBar = new ToolBar(btnNewSupplier);

        SupplierFilterPane filterPane = new SupplierFilterPane();
        try {
            filterPane.refresh(platform.getDataManager().getSupplierManager().fetchAll(false));
        } catch (BRADException se) {
            platform.getPromptUtilities().exception("Could not fetch Suppliers List", se);
        }
        filterPane.setPadding(new Insets(10));
        final SupplierTable table = filterPane.getTable();
        table.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() > 1) {
                Supplier supplier = table.getSelectionModel().getSelectedItem();
                if (supplier != null) {
                    displaySupplierDetails(supplier);
                    try {
                        filterPane.refresh(platform.getDataManager().getSupplierManager().fetchAll(false));
                    } catch (BRADException se) {
                        platform.getPromptUtilities().exception("Could not fetch Suppliers List", se);
                    }
                }
            }
        });

        BorderPane borderPane = new BorderPane(filterPane);
        borderPane.setTop(toolBar);

        btnNewSupplier.setOnAction(event -> {
            btnNewSupplierClicked();
            try {
                filterPane.refresh(platform.getDataManager().getSupplierManager().fetchAll(false));
            } catch (BRADException se) {
                platform.getPromptUtilities().exception("Could not fetch Suppliers List", se);
            }
        });
        platform.displayPane("Supplier List", borderPane);
    }

    private void btnNewSupplierClicked() {
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);

        BorderPane root = new BorderPane();

        FXMLLoader loader;

        try {
            loader = new FXMLLoader(getClass().getResource("forms/supplier_details_form.fxml"));

            SupplierDetailsFormController controller =  new SupplierDetailsFormController();
            loader.setController(controller);

            controller.setDesktopPlatform(platform);
            controller.setStage(stage);

            root = loader.load();
        } catch (IOException ex) {
            platform.getPromptUtilities().exception("Error Loading Form", ex);
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add(platform.getDefaultStylesheet());
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            event.consume();
            if (platform.getPromptUtilities().ask("Exit", "This will undo any unsaved data."))
                stage.close();
        });
        stage.showAndWait();
    }

    private void displaySupplierDetails(Supplier supplier) {
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);

        BorderPane root = new BorderPane();

        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("forms/supplier_details_form.fxml"));

            SupplierDetailsFormController controller =  new SupplierDetailsFormController();
            loader.setController(controller);

            controller.setDesktopPlatform(platform);
            controller.setStage(stage);

            root = loader.load();
            controller.updateFields(supplier);
        } catch (IOException ex) {
            platform.getPromptUtilities().exception("Error Loading Form", ex);
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add(platform.getDefaultStylesheet());
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            event.consume();
            if (platform.getPromptUtilities().ask("Exit", "This will undo any unsaved data."))
                stage.close();
        });
        stage.showAndWait();
    }

}
