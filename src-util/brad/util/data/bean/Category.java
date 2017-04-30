package brad.util.data.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category extends IDBean {

    private String _title, _notes;
    private StringProperty title, notes;

    {
        _title = _notes = "";
    }

    public Category() {
        super();
    }

    public String getTitle() {
        if (title == null)
            return _title;
        else
            return title.get();
    }

    public StringProperty titleProperty() {
        if (title == null)
            title = new SimpleStringProperty(this, "title", _title);
        return title;
    }

    public void setTitle(String title) {
        if (this.title == null)
            _title = title;
        else
            this.title.set(title);
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
        return getTitle();
    }
}
