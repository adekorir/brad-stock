package brad.stock.sdk.data.managers;

import brad.util.data.BeanManager;
import brad.util.data.bean.Category;
import brad.util.data.bean.SubCategory;
import brad.util.sys.BRADException;

import java.util.List;

public interface CategoryManager extends BeanManager<Category> {
    long createSubCategory(SubCategory category) throws BRADException;
    SubCategory fetchSubCategory(long id) throws BRADException;
    List<SubCategory> fetchAllSubCategories(boolean condition) throws BRADException;
    void updateSubCategory(SubCategory category) throws BRADException;
    void deleteSubCategory(SubCategory category) throws BRADException;
}
