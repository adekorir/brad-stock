package brad.util.data.bean;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User extends IDBean {

    private String _username, _displayName;
    private StringProperty username, displayName;
    private boolean _active;
    private BooleanProperty active;

    {
        _username = "";
        _displayName = "";
        _active = false;
    }

    public User() {
        super();
    }

    public String getUsername() {
        if (username == null)
            return _username;
        else
            return username.get();
    }

    public StringProperty usernameProperty() {
        if (username == null)
            username = new SimpleStringProperty(this,"username", _username);
        return username;
    }

    public void setUsername(String username) {
        if (this.username == null)
            _username = username;
        else
            this.username.set(username);
    }

    public String getDisplayName() {
        if (displayName == null)
            return _displayName;
        else
            return displayName.get();
    }

    public StringProperty displayNameProperty() {
        if (displayName == null)
            displayName = new SimpleStringProperty(this, "displayName", _displayName);
        return displayName;
    }

    public void setDisplayName(String displayName) {
        if (this.displayName == null)
            _displayName = displayName;
        else
            this.displayName.set(displayName);
    }

    public boolean isActive() {
        if (active == null)
            return _active;
        else
            return active.get();
    }

    public BooleanProperty activeProperty() {
        if (active == null)
            active = new SimpleBooleanProperty(this, "active", _active);
        return active;
    }

    public void setActive(boolean active) {
        if (this.active == null)
            _active = active;
        else
            this.active.set(active);
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
