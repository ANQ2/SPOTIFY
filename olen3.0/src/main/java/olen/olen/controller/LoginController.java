package olen.olen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import olen.olen.Services.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Отображение формы входа
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model, RedirectAttributes redirectAttributes) {
        // Проверка учетных данных с помощью UserService
        if (userService.authenticateUser(email, password)) {
            // Если аутентификация успешна, сохраняем имя пользователя в сессии
            redirectAttributes.addFlashAttribute("username", email);
            return "redirect:/profile"; // Перенаправление на страницу профиля
        } else {
            model.addAttribute("error", "Invalid email or password"); // Добавляем сообщение об ошибке
            return "login"; // Повторное отображение формы входа с сообщением об ошибке
        }
    }
}
