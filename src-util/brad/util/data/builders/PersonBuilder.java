package brad.util.data.builders;

import brad.util.data.BeanBuilder;
import brad.util.data.bean.Person;

@Deprecated
public class PersonBuilder implements BeanBuilder<Person> {

    private Person person;

    {
        person = new Person();
    }

    private PersonBuilder() {
        super();
    }

    @Override
    public Person build() {
        return person;
    }
}
