package com.sda.userSda.controller.old;

import com.sda.userSda.model.User;
import com.sda.userSda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/prettyusers")
public class UserServlet extends HttpServlet {

    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAll();
        users.forEach(System.out::println);
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<title>");
        writer.println("Pretty users");
        writer.println("</title>");
        writer.println("<body>");
        writer.println("<h1>Uzytkownicy</h1>");
        for (User user : users) {
            writer.println("<div>");
            writer.println(user.getFirstName() + " | " + user.getLastName() + " | " + user.getBirthDate());
            writer.println("</div>");
        }
        writer.println("</body>");
        writer.println("</html>");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
