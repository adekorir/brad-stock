package brad.stock.sdk.api;

import brad.stock.sdk.data.StockDataManager;
import brad.util.sys.PromptUtilities;
import javafx.scene.Node;

public interface DesktopPlatform {
    StockDataManager getDataManager();
    PromptUtilities getPromptUtilities();
    String getDefaultStylesheet();
    void displayPane(final String title, Node pane);
}
