package brad.stock.sdk.data.managers;

import brad.stock.sdk.data.beans.Product;
import brad.util.data.BeanManager;
import brad.util.sys.BRADException;

public interface ProductManager extends BeanManager<Product> {
    void discontinueProduct(long id) throws BRADException;
}
