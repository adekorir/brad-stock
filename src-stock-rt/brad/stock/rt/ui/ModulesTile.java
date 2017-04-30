package brad.stock.rt.ui;

import brad.stock.sdk.api.StockModule;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ModulesTile extends Tile {

    private StockModule module;

    public ModulesTile(StockModule stockModule) {
        super();

        module = stockModule;

        final String ICON_URL = (module.getClass().getResource("icon.png") == null) ?
                getClass().getResource("img/default.png").toExternalForm() :
                module.getClass().getResource("icon.png").toExternalForm();

        ImageView icon = new ImageView(ICON_URL);

        icon.setFitWidth(65);
        icon.setFitHeight(65);
        icon.setPreserveRatio(true);

        setCenter(icon);
        setBottom(new HBox(new HSpacer(), new Label(module.getName()), new HSpacer()));
        setId("modules-tile");
        setCursor(Cursor.HAND);
    }

    @Override
    public String getTitle() {
        return module.getName();
    }
}
