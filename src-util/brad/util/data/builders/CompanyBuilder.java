package brad.util.data.builders;

import brad.util.data.BeanBuilder;
import brad.util.data.bean.Company;

@Deprecated
public class CompanyBuilder implements BeanBuilder<Company> {

    private Company company;

    {
        company = new Company();
    }

    private CompanyBuilder() {
        super();
    }

    public CompanyBuilder id(long id) {
        company.setId(id);
        return this;
    }

    public CompanyBuilder name(String name) {
        company.setName(name);
        return this;
    }

    public CompanyBuilder active(boolean active) {
        company.setActive(active);
        return this;
    }

    public CompanyBuilder code(String code) {
        company.setCode(code);
        return this;
    }

    @Override
    public Company build() {
        return company;
    }
}
