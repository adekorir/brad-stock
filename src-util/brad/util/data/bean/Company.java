package brad.util.data.bean;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Company extends IDBean {

    private final long serialVersionUID = 12L;

    private String _code, _name, _notes;
    private StringProperty code, name, notes;

    private boolean _active;
    private BooleanProperty active;

    {
        _code = "";
        _name = "";

        _active = false;
    }

    public Company() {
        super();
    }

    public String getCode() {
        if (code == null)
            return _code;
        else
            return code.get();
    }

    public StringProperty codeProperty() {
        if (code == null)
            code = new SimpleStringProperty(this, "code", _code);
        return code;
    }

    public void setCode(String code) {
        if (this.code == null)
            _code = code;
        else
            this.code.set(code);
    }

    public String getName() {
        if (name == null)
            return _name;
        else
            return name.get();
    }

    public StringProperty nameProperty() {
        if (name == null)
            name = new SimpleStringProperty(this, "name", _name);
        return name;
    }

    public void setName(String name) {
        if (this.name == null)
            _name = name;
        else
            this.name.set(name);
    }

    public String getNotes() {
        if (notes == null)
            return _notes;
        else
            return notes.get();
    }

    public StringProperty notesProperty() {
        if (notes == null)
            notes = new SimpleStringProperty(this, "notes", _notes);
        return notes;
    }

    public void setNotes(String notes) {
        if (this.notes == null)
            _notes = notes;
        else
            this.notes.set(notes);
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
        return getName();
    }
}
