package GandA.corporation.APK.service;

import java.util.List;

import GandA.corporation.APK.model.PlannedProduction;
import GandA.corporation.APK.repo.PlannedProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannedProductionService {
    @Autowired
    private PlannedProductionRepository repo;

    public List<PlannedProduction> listAll() {
        return repo.findAll();
    }

    public void save(PlannedProduction product) {
        repo.save(product);
    }

    public PlannedProduction get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}