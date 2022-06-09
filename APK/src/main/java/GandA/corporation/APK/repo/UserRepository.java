package GandA.corporation.APK.repo;

import GandA.corporation.APK.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u where u.company_code is null")
    List<User> getUserWhereCompany_codeIsNull();

    @Query("SELECT u FROM User u where u.company_code = :company_code")
    User getUserByCompany_code(Long company_code);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getUserById(Long id);
}
