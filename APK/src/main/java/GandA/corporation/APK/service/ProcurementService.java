package GandA.corporation.APK.service;

import java.util.List;

import GandA.corporation.APK.model.Procurement;
import GandA.corporation.APK.repo.ProcurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcurementService {
    @Autowired
    private ProcurementRepository repo;

    public List<Procurement> listAll() {
        return repo.findAll();
    }

    public void save(Procurement product) {
        repo.save(product);
    }

    public Procurement get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}