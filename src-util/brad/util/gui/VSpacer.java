package brad.util.gui;

import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class VSpacer extends Region {
    public VSpacer() {
        super();
        VBox.setVgrow(this, Priority.ALWAYS);
    }
}
