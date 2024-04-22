package olen.olen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
        // Здесь должна быть ваша логика проверки учетных данных пользователя
        if (isValidCredentials(email, password)) {
            return "welcome"; // Перенаправление на страницу приветствия после успешного входа
        } else {
            model.addAttribute("error", "Invalid email or password"); // Добавляем сообщение об ошибке
            return "login"; // Повторно отображаем форму логина с сообщением об ошибке
        }
    }

    private boolean isValidCredentials(String email, String password) {
        // Здесь должна быть ваша логика проверки учетных данных пользователя
        // В данном примере всегда возвращается true
        return true;
    }
}
