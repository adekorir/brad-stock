package brad.stock.rt;

import brad.stock.rt.sys.ModuleCache;
import brad.stock.rt.ui.ModulesTile;
import brad.stock.rt.ui.Tile;
import brad.stock.sdk.api.DesktopPlatform;
import brad.stock.sdk.api.StockModule;
import brad.stock.sdk.data.StockDataManager;
import brad.util.sys.BRADException;
import brad.util.sys.JarClassloader;
import brad.util.sys.PromptUtilities;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ServiceLoader;

public class BootstrapApplication extends Application implements DesktopPlatform {

    private ModuleCache moduleCache;
    private StockDataManager dataManager;
    private StackPane stackPane;
    private RuntimePromptUtilities promptUtilities;
    private String defaultStyleSheet;

    {
        defaultStyleSheet = getClass().getResource("style.css").toExternalForm();
        moduleCache = new ModuleCache();
        stackPane = new StackPane();
        promptUtilities = new RuntimePromptUtilities(defaultStyleSheet);
    }

    @Override
    public void init() throws Exception {
        final File folder = new File("modules");
        final JarClassloader cl = new JarClassloader(new URL[]{});

        try {
            for (File file: folder.listFiles())
                cl.addJarFile(file);
        } catch (NullPointerException ex) {
            Platform.runLater(() -> promptUtilities.exception("Error loading modules folder", ex));
        }

        // load data managers
        for (StockDataManager manager : ServiceLoader.load(StockDataManager.class, cl)) {
            if (manager.getName().equalsIgnoreCase("Derby(Embedded)"))
                dataManager = manager;
        }

        // open the default data manager
        try {
            if (dataManager != null) {
                dataManager.open("sample\\stock_db", "", "");
            }
        } catch(BRADException ex) {
            Platform.runLater(() -> promptUtilities.exception("Error opening data manager", ex));
        }

        // load desktop modules
        for (StockModule module: ServiceLoader.load(StockModule.class, cl)) {
            module.initialize(this);

            moduleCache.add(module);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final TilePane tilePane = new TilePane(Orientation.HORIZONTAL, 10, 10);
        tilePane.setPadding(new Insets(10));

        final ObservableList<Tile> tiles = FXCollections.observableArrayList();
        for (StockModule module: moduleCache) {
            final Tile tile = new ModulesTile(module);
            tile.setOnMouseClicked(event -> module.launch());
            tiles.add(tile);
            tilePane.getChildren().add(tile);
        }

        final TextField fldFilter = new TextField();
        fldFilter.setPromptText("Filter tiles (Press ESC to clear)");
        fldFilter.setTooltip(new Tooltip("Filter tiles (Press ESC to clear)"));
        fldFilter.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE)
                fldFilter.clear();
        });
        fldFilter.textProperty().addListener(((observable, oldValue, newValue) -> {
            tilePane.getChildren().clear();
            for (Tile tile: tiles) {
                if (tile.getTitle().trim().toLowerCase().contains(newValue.trim().toLowerCase()))
                    tilePane.getChildren().add(tile);
            }
        }));
        HBox.setHgrow(fldFilter, Priority.ALWAYS);

        final ToolBar filterPane = new ToolBar(
                new ImageView(getClass().getResource("ui/img/search_dark_24x24.png").toExternalForm()), fldFilter);

        final BorderPane tileFilter = new BorderPane(tilePane);
        tileFilter.setTop(filterPane);

        stackPane.getChildren().add(tileFilter);

        final String[] fileMenuItems = {"_Logout", "E_xit"};
        final String[] helpMenuItems = {"_Help", "_About"};

        // menus
        MenuBar menuBar = new MenuBar();
        final Menu mnuFile = new Menu("_File");
        for (String title: fileMenuItems) {
            MenuItem menuItem = new MenuItem(title);
            menuItem.setOnAction(event -> {
                switch (title) {
                    case "E_xit":
                        if (promptUtilities.ask("Quit", "Are you sure to exit"))
                            Platform.exit();
                        break;
                    case "_Logout":
                        break;
                    default:
                        break;
                }
            });
            mnuFile.getItems().add(menuItem);
        }
        menuBar.getMenus().add(mnuFile);

        final Menu mnuModules = new Menu("_Modules");
        for (StockModule module: moduleCache) {
            MenuItem menuItem = new MenuItem(module.getName());
            menuItem.setOnAction(event -> module.launch());
            mnuModules.getItems().add(menuItem);
        }
        menuBar.getMenus().add(mnuModules);

        final Menu mnuHelp = new Menu("_Help");
        for (String title: helpMenuItems) {
            MenuItem menuItem = new MenuItem(title);
            mnuHelp.getItems().add(menuItem);
        }
        menuBar.getMenus().add(mnuHelp);

        BorderPane root = new BorderPane(stackPane);
        root.setTop(menuBar);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getDefaultStylesheet());
        promptUtilities.setDefaultStyleSheet(getDefaultStylesheet());

        primaryStage.setTitle("BRAD Stock Management");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        for (StockModule module: moduleCache)
            module.cleanUp();

        if (dataManager != null) {
            try {
                dataManager.close();
            } catch (BRADException ex) {
                promptUtilities.exception("Error closing Stock Data Manager", ex);
            }
        }
    }

    @Override
    public String getDefaultStylesheet() {
        return defaultStyleSheet;
    }

    @Override
    public void displayPane(String title, Node pane) {
        final int SIZE = stackPane.getChildren().size();

        final Node parent = stackPane.getChildren().get(SIZE-1);
        parent.setOpacity(0);

        final Label lblTitle = new Label(title.toUpperCase());
        //lblTitle.setFont(Font.font(14));
        lblTitle.setId("separator-label");

        final Separator separator = new Separator(Orientation.HORIZONTAL);
        HBox.setHgrow(separator, Priority.ALWAYS);
        separator.setId("separator-label");

        final Region hSpacer = new Region();
        HBox.setHgrow(hSpacer, Priority.ALWAYS);

        final BorderPane closablePane = new BorderPane(pane);

        final Hyperlink lnkClose = new Hyperlink("Close");
        lnkClose.setOnAction(event -> {
            //if (ask("Close", "This might undo any unsaved data.")) {
                stackPane.getChildren().remove(closablePane);
                parent.setOpacity(1.0);
            //}
            lnkClose.setVisited(false);
        });
        lnkClose.setId("separator-label");

        final HBox toolBar = new HBox(10, new Separator(Orientation.HORIZONTAL), lblTitle, separator, lnkClose);
        toolBar.setAlignment(Pos.CENTER);
        closablePane.setTop(toolBar);
        stackPane.getChildren().add(closablePane);
    }

    @Override
    public StockDataManager getDataManager() {
        return dataManager;
    }

    @Override
    public PromptUtilities getPromptUtilities() {
        return promptUtilities;
    }

    public static void main(String args[]) {
        Application.launch(args);
    }
}
