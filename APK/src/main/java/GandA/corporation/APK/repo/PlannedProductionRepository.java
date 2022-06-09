package GandA.corporation.APK.repo;

import GandA.corporation.APK.model.PlannedProduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannedProductionRepository extends JpaRepository<PlannedProduction, Long> {
}