package GandA.corporation.APK.service;

import GandA.corporation.APK.model.Sale;
import GandA.corporation.APK.repo.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repo;

    public List<Sale> listAll() {
        return repo.findAll();
    }

    public void save(Sale product) {
        repo.save(product);
    }

    public Sale get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}