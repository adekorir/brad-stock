package brad.stock.sdk.data;

import brad.stock.sdk.data.managers.ProductManager;
import brad.stock.sdk.data.managers.SupplierManager;
import brad.util.sys.BRADException;

public interface StockDataManager {
    String getName();

    void open(final String url, final String username, final String password) throws BRADException;

    SupplierManager getSupplierManager();
    ProductManager getProductManager();

    void close() throws BRADException;
}
