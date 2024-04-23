package olen.olen.Services;

import olen.olen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import olen.olen.Repository.UserRepository;

@Service
public class RegistrationService {
    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Пользователь с таким именем уже зарегестрирован");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Пользователь с таким email уже зарегестирован");
        }

        User user = new User(username, email, password);

        userRepository.save(user);
    }
}
