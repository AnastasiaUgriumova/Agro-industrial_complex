package GandA.corporation.APK.repo;

import GandA.corporation.APK.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT u FROM Company u where u.company_code = :company_code")
    Company findByCompany_code(Long company_code);

}