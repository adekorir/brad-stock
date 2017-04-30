package brad.stock.sdk.data.managers;

import brad.util.data.BeanManager;
import brad.util.data.bean.User;
import brad.util.sys.BRADException;

public interface UserManager extends BeanManager<User> {
    boolean validateUser(String username, String password) throws BRADException;
    boolean checkActive(String username) throws BRADException;
    boolean checkActive(long id) throws BRADException;
}
