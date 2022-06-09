package GandA.corporation.APK.repo;

import GandA.corporation.APK.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
}