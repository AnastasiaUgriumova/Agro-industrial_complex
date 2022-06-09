package GandA.corporation.APK.vilidator;

import GandA.corporation.APK.model.Company;
import GandA.corporation.APK.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CompanyValidator implements Validator {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Company.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Company company = (Company) o;
        if (companyRepository.findByCompany_code(company.getCompany_code()) != null) {
            errors.reject("company_code", "Duplicate.Company.company_code");
        }
    }
}