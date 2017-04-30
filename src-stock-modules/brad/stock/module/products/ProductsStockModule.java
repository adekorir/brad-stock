package brad.stock.module.products;

import brad.stock.module.products.ui.ProductsTable;
import brad.stock.sdk.api.DesktopPlatform;
import brad.stock.sdk.api.StockModule;

public class ProductsStockModule extends StockModule {

    private DesktopPlatform platform;

    @Override
    public String getName() {
        return "Products";
    }

    @Override
    public void initialize(DesktopPlatform platform) {
        this.platform = platform;
    }

    @Override
    public void launch() {
        platform.displayPane("Products Table", new ProductsTable());
    }
}
