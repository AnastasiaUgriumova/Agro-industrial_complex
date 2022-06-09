package GandA.corporation.APK.repo;

import GandA.corporation.APK.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}