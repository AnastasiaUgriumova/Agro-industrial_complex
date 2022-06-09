package GandA.corporation.APK.service;

import java.util.List;

import GandA.corporation.APK.model.Company;
import GandA.corporation.APK.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repo;

    @Autowired
    private  UserService userService;

    public List<Company> listAll() {
        return repo.findAll();
    }

    public void save(Company company) {
        repo.save(company);
    }

    public Company getByCompany_code(Long company_code){return repo.findByCompany_code(company_code);}

    public Boolean AunHaveCompany(){

        return userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getCompanyToUser() == null;
    }

    public Boolean AunCompanyIsActive(){
        return !userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getCompanyToUser().isActive();
    }

    public Company get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}