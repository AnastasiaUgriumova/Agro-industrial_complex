package GandA.corporation.APK.service;

import GandA.corporation.APK.model.Product;
import GandA.corporation.APK.model.ProductionCosts;
import GandA.corporation.APK.model.Role;
import GandA.corporation.APK.model.Sale;
import GandA.corporation.APK.repo.ProductionCostsRepository;
import GandA.corporation.APK.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repo;

    public List<Role> listAll() {
        return repo.findAll();
    }

    public Role get(Long id) {
        return repo.findById(id).get();
    }

}
