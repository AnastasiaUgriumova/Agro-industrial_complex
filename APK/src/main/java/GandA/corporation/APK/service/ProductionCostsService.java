package GandA.corporation.APK.service;

import java.util.List;

import GandA.corporation.APK.model.ProductionCosts;
import GandA.corporation.APK.repo.ProductionCostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionCostsService {
    @Autowired
    private ProductionCostsRepository repo;

    public List<ProductionCosts> listAll() {
        return repo.findAll();
    }

    public void save(ProductionCosts product) {
        repo.save(product);
    }

    public ProductionCosts get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}