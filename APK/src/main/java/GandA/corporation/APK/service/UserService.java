package GandA.corporation.APK.service;

import GandA.corporation.APK.model.Role;
import GandA.corporation.APK.model.User;
import GandA.corporation.APK.repo.RoleRepository;
import GandA.corporation.APK.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class UserService{

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    public void saveNotPassword(User user) {
        repo.save(user);
    }

    public List<User> getUserWhereCompany_codeIsNull(){
        return repo.getUserWhereCompany_codeIsNull();
    }

    public User getUserByEmail(String email){
        return repo.getUserByEmail(email);
    }

    public User getAuntUser(){
        return getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public List<User> listAll() {
        return repo.findAll();
    }

    public User get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}