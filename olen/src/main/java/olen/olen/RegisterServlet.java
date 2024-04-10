package olen.olen;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Валидация имени пользователя и пароля (добавьте логику)
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.setStatus(400);
            response.getWriter().println("Неверные данные!");
            return;
        }

        // Регистрация пользователя (добавьте логику)
        // ...

        if (userRegisteredSuccessfully) {
            response.sendRedirect("success.html");
        } else {
            response.setStatus(500);
            response.getWriter().println("Ошибка регистрации!");
        }
    }
}
