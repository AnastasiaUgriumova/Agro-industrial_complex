package GandA.corporation.APK.service;

import GandA.corporation.APK.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
