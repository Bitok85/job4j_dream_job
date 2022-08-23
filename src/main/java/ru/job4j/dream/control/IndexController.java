package ru.job4j.dream.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dream.model.User;
import ru.job4j.dream.utils.UserCheck;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        UserCheck.checkForGuestOrExisting(model, session);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }
}
