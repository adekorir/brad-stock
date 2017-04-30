package brad.stock.data.builders;

import brad.util.data.BeanBuilder;
import brad.util.data.bean.SubCategory;

@Deprecated
public class SubCategoryBuilder implements BeanBuilder<SubCategory> {

    private SubCategory subCategory;

    {
        subCategory = new SubCategory();
    }

    private SubCategoryBuilder() {
        super();
    }

    public static SubCategoryBuilder create() {
        return new SubCategoryBuilder();
    }

    private SubCategoryBuilder id(long val) {
        subCategory.setId(val);
        return this;
    }

    private SubCategoryBuilder categoryId(long val) {
        subCategory.setCategoryId(val);
        return this;
    }

    private SubCategoryBuilder name(String val) {
        subCategory.setTitle(val);
        return this;
    }

    private SubCategoryBuilder notes(String val) {
        subCategory.setNotes(val);
        return this;
    }

    @Override
    public SubCategory build() {
        return subCategory;
    }
}
