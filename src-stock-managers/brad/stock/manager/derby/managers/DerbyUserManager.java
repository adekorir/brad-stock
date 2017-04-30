package brad.stock.manager.derby.managers;

import brad.stock.manager.derby.DerbyCRUDOperations;
import brad.stock.sdk.data.managers.UserManager;
import brad.util.data.bean.User;
import brad.util.sys.BRADException;

import java.util.List;

public class DerbyUserManager implements UserManager {

    private DerbyCRUDOperations crudOperations;

    public DerbyUserManager(DerbyCRUDOperations crudOperations) {
        super();

        this.crudOperations = crudOperations;
    }

    @Override
    public long create(User user) throws BRADException {
        return 0;
    }

    @Override
    public User fetch(long id) throws BRADException {
        return null;
    }

    @Override
    public boolean validateUser(String username, String password) throws BRADException {
        return false;
    }

    @Override
    public List<User> fetchAll(boolean condition) throws BRADException {
        return null;
    }

    @Override
    public void update(User user) throws BRADException {

    }

    @Override
    public boolean checkActive(String username) throws BRADException {
        return false;
    }

    @Override
    public void delete(User user) throws BRADException {

    }

    @Override
    public boolean checkActive(long id) throws BRADException {
        return false;
    }
}
