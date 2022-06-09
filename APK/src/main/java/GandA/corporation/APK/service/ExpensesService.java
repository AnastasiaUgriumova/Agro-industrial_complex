package GandA.corporation.APK.service;

import java.util.List;

import GandA.corporation.APK.model.Expenses;
import GandA.corporation.APK.repo.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesService {
    @Autowired
    private ExpensesRepository repo;

    public List<Expenses> listAll() {
        return repo.findAll();
    }

    public void save(Expenses product) {
        repo.save(product);
    }

    public Expenses get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}