package GandA.corporation.APK.repo;

import GandA.corporation.APK.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}