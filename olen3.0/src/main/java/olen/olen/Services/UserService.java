package olen.olen.Services;

import olen.olen.model.User;
import olen.olen.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true; // Пользователь аутентифицирован успешно
        }
        return false; // Неправильный email или пароль
    }
}
