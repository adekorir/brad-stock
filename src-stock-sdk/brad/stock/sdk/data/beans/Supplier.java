package brad.stock.sdk.data.beans;

import brad.util.data.bean.Company;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Supplier extends Company {

    private String _businessPone, _mobile, _fax, _postalCode, _email, _webPage,
            _address, _street, _city, _region, _country;
    private StringProperty businessPhone, mobile, fax, postalCode, email, webPage,
            address, street, city, region, country;

    {
        _businessPone = "";
        _mobile = "";
        _fax = "";
        _postalCode = "";
        _email = "";
        _webPage = "";
        _address = "";
        _street = "";
        _city = "";
        _region = "";
        _country = "";
    }

    public Supplier() {
        super();
    }

    public String getBusinessPhone() {
        if (businessPhone == null)
            return _businessPone;
        else
            return businessPhone.get();
    }

    public StringProperty businessPhoneProperty() {
        if (businessPhone == null)
            businessPhone = new SimpleStringProperty(this, "businessPhone", _businessPone);
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        if (this.businessPhone == null)
            _businessPone = businessPhone;
        else
            this.businessPhone.set(businessPhone);
    }

    public String getMobile() {
        if (mobile == null)
            return _mobile;
        else
            return mobile.get();
    }

    public StringProperty mobileProperty() {
        if (mobile == null)
            mobile = new SimpleStringProperty(this, "mobile", _mobile);
        return mobile;
    }

    public void setMobile(String mobile) {
        if (this.mobile == null)
            _mobile = mobile;
        else
            this.mobile.set(mobile);
    }

    public String getFax() {
        if (fax == null)
            return _fax;
        else
            return fax.get();
    }

    public StringProperty faxProperty() {
        if (fax == null)
            fax = new SimpleStringProperty(this, "fax", _fax);
        return fax;
    }

    public void setFax(String fax) {
        if (this.fax == null)
            _fax = fax;
        else
            this.fax.set(fax);
    }

    public String getPostalCode() {
        if (postalCode == null)
            return _postalCode;
        else
            return postalCode.get();
    }

    public StringProperty postalCodeProperty() {
        if (postalCode == null)
            postalCode = new SimpleStringProperty(this, "postalCode", _postalCode);
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        if (this.postalCode == null)
            _postalCode = postalCode;
        else
            this.postalCode.set(postalCode);
    }

    public String getEmail() {
        if (email == null)
            return _email;
        else
            return email.get();
    }

    public StringProperty emailProperty() {
        if (email == null)
            email = new SimpleStringProperty(this, "email", _email);
        return email;
    }

    public void setEmail(String email) {
        if (this.email == null)
            _email = email;
        else
            this.email.set(email);
    }

    public String getWebPage() {
        if (webPage == null)
            return _webPage;
        else
            return webPage.get();
    }

    public StringProperty webPageProperty() {
        if (webPage == null)
            webPage = new SimpleStringProperty(this, "webPage", _webPage);
        return webPage;
    }

    public void setWebPage(String webPage) {
        if (this.webPage == null)
            _webPage = webPage;
        else
            this.webPage.set(webPage);
    }

    public String getAddress() {
        if (address == null)
            return _address;
        else
            return address.get();
    }

    public StringProperty addressProperty() {
        if (address == null)
            address = new SimpleStringProperty(this, "address", _address);
        return address;
    }

    public void setAddress(String address) {
        if (this.address == null)
            _address = address;
        else
            this.address.set(address);
    }

    public String getStreet() {
        if (street == null)
            return _street;
        else
            return street.get();
    }

    public StringProperty streetProperty() {
        if (street == null)
            street = new SimpleStringProperty(this, "street", _street);
        return street;
    }

    public void setStreet(String street) {
        if (this.street == null)
            _street = street;
        else
            this.street.set(street);
    }

    public String getCity() {
        if (city == null)
            return _city;
        else
            return city.get();
    }

    public StringProperty cityProperty() {
        if (city == null)
            city = new SimpleStringProperty(this, "city", _city);
        return city;
    }

    public void setCity(String city) {
        if (this.city == null)
            _city = city;
        else
            this.city.set(city);
    }

    public String getRegion() {
        if (region == null)
            return _region;
        else
            return region.get();
    }

    public StringProperty regionProperty() {
        if (region == null)
            region = new SimpleStringProperty(this, "region", _region);
        return region;
    }

    public void setRegion(String region) {
        if (this.region == null)
            _region = region;
        else
            this.region.set(region);
    }

    public String getCountry() {
        if (country == null)
            return _country;
        else
            return country.get();
    }

    public StringProperty countryProperty() {
        if (country == null)
            country = new SimpleStringProperty(this, "country", _country);
        return country;
    }

    public void setCountry(String country) {
        if (this.country == null)
            _country = country;
        else
            this.country.set(country);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
