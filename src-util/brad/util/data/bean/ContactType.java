package brad.util.data.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContactType extends IDBean {

    private String _description, _notes;
    private StringProperty description, notes;

    {
        _description = "";
        _notes = "";
    }

    public ContactType() {
        super();
    }

    public String getDescription() {
        if (description == null)
            return _description;
        else
            return description.get();
    }

    public StringProperty descriptionProperty() {
        if (description == null)
            description = new SimpleStringProperty(this, "description", _description);
        return description;
    }

    public void setDescription(String description) {
        if (this.description == null)
            _description = description;
        else
            this.description.set(description);
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

    @Override
    public String toString() {
        return getDescription();
    }
}
