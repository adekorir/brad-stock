package brad.stock.rt.ui;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

class HSpacer extends Region {
    {
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}
