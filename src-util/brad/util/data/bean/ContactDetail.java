package brad.util.data.bean;

import javafx.beans.property.*;

public class ContactDetail extends IDBean {

    private long _companyId;
    private LongProperty companyId;

    private ContactType _contactType;
    private ObjectProperty<ContactType> contactType;

    private String _detail;
    private StringProperty detail;

    {
        _companyId = -1;
        _contactType = null;
        _detail = "";
    }

    public ContactDetail() {
        super();
    }

    public long getCompanyId() {
        if (companyId == null)
            return _companyId;
        else
            return companyId.get();
    }

    public LongProperty companyIdProperty() {
        if (companyId == null)
            companyId = new SimpleLongProperty(this, "companyId", _companyId);
        return companyId;
    }

    public void setCompanyId(long companyId) {
        if (this.companyId == null)
            _companyId = companyId;
        else
            this.companyId.set(companyId);
    }

    public ContactType getContactType() {
        if (contactType == null)
            return _contactType;
        else
            return contactType.get();
    }

    public ObjectProperty<ContactType> contactTypeProperty() {
        if (contactType == null)
            contactType = new SimpleObjectProperty<>(this, "contactType", _contactType);
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        if (this.contactType == null)
            _contactType = contactType;
        else
            this.contactType.set(contactType);
    }

    public String getDetail() {
        if (detail == null)
            return _detail;
        else
            return detail.get();
    }

    public StringProperty detailProperty() {
        if (detail == null)
            detail = new SimpleStringProperty(this, "detail", _detail);
        return detail;
    }

    public void setDetail(String detail) {
        if (this.detail == null)
            _detail = detail;
        else
            this.detail.set(detail);
    }

    @Override
    public String toString() {
        return getDetail();
    }
}
