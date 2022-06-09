package GandA.corporation.APK.repo;

import GandA.corporation.APK.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}