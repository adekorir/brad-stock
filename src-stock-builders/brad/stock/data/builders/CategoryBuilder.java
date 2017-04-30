package brad.stock.data.builders;

import brad.util.data.BeanBuilder;
import brad.util.data.bean.Category;

@Deprecated
public class CategoryBuilder implements BeanBuilder<Category> {

    private Category category;

    {
        category = new Category();
    }

    private CategoryBuilder() {
        super();
    }

    public static CategoryBuilder create() {
        return new CategoryBuilder();
    }

    private CategoryBuilder id(long val) {
        category.setId(val);
        return this;
    }

    private CategoryBuilder name(String val) {
        category.setTitle(val);
        return this;
    }

    private CategoryBuilder notes(String val) {
        category.setNotes(val);
        return this;
    }

    @Override
    public Category build() {
        return category;
    }
}
