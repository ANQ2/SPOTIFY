package olen.olen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String userProfile(Model model) {
        // Ваши данные пользователя могут быть получены из базы данных или другого источника
        // Здесь я использую примерные данные
        model.addAttribute("username");
        model.addAttribute("email");
        return "profile"; // возвращает имя вашего HTML файла (например, "profile.html")
    }
}
