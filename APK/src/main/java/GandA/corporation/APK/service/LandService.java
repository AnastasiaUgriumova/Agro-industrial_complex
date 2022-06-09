package GandA.corporation.APK.service;

import java.util.List;

import GandA.corporation.APK.model.Land;
import GandA.corporation.APK.repo.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandService {

    @Autowired
    private LandRepository repo;

    public List<Land> listAll() {
        return repo.findAll();
    }

    public void save(Land land) {
        repo.save(land);
    }

    public Land get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}