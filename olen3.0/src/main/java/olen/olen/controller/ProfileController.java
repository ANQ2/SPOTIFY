package olen.olen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import olen.olen.model.User;
import olen.olen.Repository.UserRepository;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        // Получаем адрес электронной почты пользователя из сессии
        String email = (String) session.getAttribute("username");
        if (email != null) {
            // Ищем пользователя в базе данных по адресу электронной почты
            User user = userRepository.findByEmail(email);
            if (user != null) {
                model.addAttribute("user", user);
                return "profile"; // Отображение страницы профиля
            }
        }
        return "redirect:/login"; // Перенаправление на страницу входа, если пользователь не аутентифицирован
    }
}
