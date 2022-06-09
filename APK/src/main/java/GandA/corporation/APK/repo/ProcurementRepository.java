package GandA.corporation.APK.repo;

import GandA.corporation.APK.model.Procurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcurementRepository extends JpaRepository<Procurement, Long> {
}