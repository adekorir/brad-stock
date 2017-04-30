package brad.stock.manager.sqlite.managers;

import brad.stock.manager.sqlite.SQLiteCRUDOperations;
import brad.stock.sdk.data.managers.CategoryManager;
import brad.util.data.bean.Category;
import brad.util.data.bean.SubCategory;
import brad.util.sys.BRADException;

import java.util.List;

public class SQLiteCategoryManager implements CategoryManager {

    private SQLiteCRUDOperations crudOperations;

    public SQLiteCategoryManager(SQLiteCRUDOperations crudOperations) {
        super();
        this.crudOperations = crudOperations;
    }

    // categories section
    @Override
    public long create(Category category) throws BRADException {
        return 0;
    }

    @Override
    public Category fetch(long id) throws BRADException {
        return null;
    }

    @Override
    public List<Category> fetchAll(boolean condition) throws BRADException {
        return null;
    }

    @Override
    public void update(Category category) throws BRADException {

    }

    @Override
    public void delete(Category category) throws BRADException {

    }


    // sub categories section
    @Override
    public long createSubCategory(SubCategory category) throws BRADException {
        return 0;
    }

    @Override
    public SubCategory fetchSubCategory(long id) throws BRADException {
        return null;
    }

    @Override
    public List<SubCategory> fetchAllSubCategories(boolean condition) throws BRADException {
        return null;
    }

    @Override
    public void updateSubCategory(SubCategory category) throws BRADException {

    }

    @Override
    public void deleteSubCategory(SubCategory category) throws BRADException {

    }
}
