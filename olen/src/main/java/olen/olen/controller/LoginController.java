package olen.olen.controller;

import olen.olen.Repository.UserRepository;
import olen.olen.Services.UserAuthenticationService;
import olen.olen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public LoginController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
        // Проверяем учетные данные пользователя
        if (userAuthenticationService.isValidCredentials(email, password)) {
            return "profile"; // Перенаправление на страницу приветствия после успешного входа
        } else {
            model.addAttribute("error", "Invalid email or password"); // Добавляем сообщение об ошибке
            return "login"; // Повторно отображаем форму логина с сообщением об ошибке
        }
    }
}
