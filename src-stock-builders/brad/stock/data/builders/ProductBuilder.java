package brad.stock.data.builders;

import brad.stock.sdk.data.beans.Product;
import brad.util.data.BeanBuilder;
import brad.util.data.bean.SubCategory;

@Deprecated
public class ProductBuilder implements BeanBuilder<Product> {

    Product product;

    {
        product = new Product();
    }

    private ProductBuilder() {
        super();
    }

    public static ProductBuilder create() {
        return new ProductBuilder();
    }

    public ProductBuilder id(long val) {
        product.setId(val);
        return this;
    }

    public ProductBuilder subCategory(SubCategory val) {
        product.setSubCategory(val);
        return this;
    }

    public ProductBuilder description(String val) {
        product.setDescription(val);
        return this;
    }

    public ProductBuilder notes(String val) {
        product.setNotes(val);
        return this;
    }

    public ProductBuilder purchasePrice(double val) {
        product.setPurchasePrice(val);
        return this;
    }

    public ProductBuilder salePrice(double val) {
        product.setSalePrice(val);
        return this;
    }
    public ProductBuilder reorderLimit(double val) {
        product.setReorderLimit(val);
        return this;
    }
    public ProductBuilder reorderAmount(double val) {
        product.setReorderAmount(val);
        return this;
    }
    public ProductBuilder discontinued(boolean val) {
        product.setDiscontinued(val);
        return this;
    }

    @Override
    public Product build() {
        return product;
    }
}
