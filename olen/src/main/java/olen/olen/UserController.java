package olen.olen;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService; // Подразумевается, что у вас есть сервис для работы с пользователями

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.save(users); // Сохраняем пользователя в базу данных
        return "User registered successfully";
    }
}
