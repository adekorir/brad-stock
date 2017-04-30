package brad.stock.sdk.api;

import javafx.scene.image.Image;

public abstract class StockModule implements Comparable<StockModule> {

    public void initialize(DesktopPlatform desktopPlatform) {
    }

    public abstract void launch();

    public String getID() {
        return getClass().getCanonicalName();
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    public String getVersion() {
        return getClass().getSimpleName();
    }

    public String getAuthor() {
        return "";
    }

    public String getDescription() {
        return "";
    }

    public Image getIcon() {
        return null;
    }

    public void cleanUp() {
    }

    @Override
    public int compareTo(StockModule o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public String toString() {
        return getName();
    }

}
