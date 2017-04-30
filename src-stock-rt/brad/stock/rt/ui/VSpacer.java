package brad.stock.rt.ui;

import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

class VSpacer extends Region {
    {
        VBox.setVgrow(this, Priority.ALWAYS);
    }
}
