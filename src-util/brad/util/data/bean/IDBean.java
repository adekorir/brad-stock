package brad.util.data.bean;

import brad.util.data.Bean;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

public class IDBean implements Bean {

    private long _id;
    private LongProperty id;

    {
        _id = -1;
    }

    public IDBean() {
        super();
    }

    public long getId() {
        if (id == null)
            return _id;
        else
            return id.get();
    }

    public LongProperty idProperty() {
        if (id == null)
            id = new SimpleLongProperty(this, "id", _id);
        return id;
    }

    public void setId(long id) {
        if (this.id == null)
            _id = id;
        else
            this.id.set(id);
    }
}
