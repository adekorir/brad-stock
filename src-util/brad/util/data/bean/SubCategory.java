package brad.util.data.bean;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

public class SubCategory extends Category {

    private long _categoryId;
    private LongProperty categoryId;

    {
        _categoryId = 1;
    }

    public SubCategory() {
        super();
    }

    public long getCategoryId() {
        if (categoryId == null)
            return _categoryId;
        else
            return categoryId.get();
    }

    public LongProperty categoryIdProperty() {
        if (categoryId == null)
            categoryId = new SimpleLongProperty(this, "categoryId", _categoryId);
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        if (this.categoryId == null)
            _categoryId = categoryId;
        else
            this.categoryId.set(categoryId);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
