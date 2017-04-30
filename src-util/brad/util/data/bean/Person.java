package brad.util.data.bean;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Person extends Company {

    private final long serialVersionUID = 23L;

    private String _lastName, _firstName, _otherNames;
    private StringProperty lastName, firstName, otherNames;

    private LocalDate _dateOfBirth;
    private ObjectProperty<LocalDate> dateOfBirth;

    private Gender _gender;
    private ObjectProperty<Gender> gender;

    {
        _lastName = "";
        _firstName = "";
        _otherNames = "";
        _dateOfBirth = LocalDate.now();
        _gender = Gender.OTHER;
    }

    public Person() {
        super();
    }

    public String getLastName() {
        if (lastName == null)
            return _lastName;
        else
            return lastName.get();
    }

    public StringProperty lastNameProperty() {
        if (lastName == null)
            lastName = new SimpleStringProperty(this, "lastName", _lastName);
        return lastName;
    }

    public void setLastName(String lastName) {
        if (this.lastName == null)
            _lastName = lastName;
        else
            this.lastName.set(lastName);
        updateName();
    }

    public String getFirstName() {
        if (firstName == null)
            return _firstName;
        else
            return firstName.get();
    }

    public StringProperty firstNameProperty() {
        if (firstName == null)
            firstName = new SimpleStringProperty(this, "firstName", _firstName);
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (this.firstName == null)
            _firstName = firstName;
        else
            this.firstName.set(firstName);
        updateName();
    }

    public String getOtherNames() {
        if (otherNames == null)
            return _otherNames;
        else
            return otherNames.get();
    }

    public StringProperty otherNamesProperty() {
        if (otherNames == null)
            otherNames = new SimpleStringProperty(this, "otherNames", _otherNames);
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        if (this.otherNames == null)
            _otherNames = otherNames;
        else
            this.otherNames.set(otherNames);
        updateName();
    }

    public LocalDate getDateOfBirth() {
        if (dateOfBirth == null)
            return _dateOfBirth;
        else
            return dateOfBirth.get();
    }

    public ObjectProperty<LocalDate> dateOfBirthProperty() {
        if (dateOfBirth == null)
            dateOfBirth = new SimpleObjectProperty<>(this, "dateOfBirth", _dateOfBirth);
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (this.dateOfBirth == null)
            _dateOfBirth = dateOfBirth;
        else
            this.dateOfBirth.set(dateOfBirth);
    }

    public Gender getGender() {
        if (gender == null)
            return _gender;
        else
            return gender.get();
    }

    public ObjectProperty<Gender> genderProperty() {
        if (gender == null)
            gender = new SimpleObjectProperty<>(this, "gender", _gender);
        return gender;
    }

    public void setGender(Gender gender) {
        if (this.gender == null)
            _gender = gender;
        else
            this.gender.set(gender);
    }

    private void updateName() {
        final String lName = getLastName() == null ? "" : getLastName().trim();
        final String fName = getFirstName() == null ? "" : getFirstName().trim();
        final String oName = getOtherNames() == null ? "" : getOtherNames().trim();

        setName(lName + ", " + fName + (oName.isEmpty() ? "" : " " + oName));
    }

    public enum Gender {
        MALE("Male"), FEMALE("Female"), OTHER("Other");

        final String genderAsString;
        Gender(String gender) {
            genderAsString = gender;
        }

        @Override
        public String toString() {
            return genderAsString;
        }
    }
}
