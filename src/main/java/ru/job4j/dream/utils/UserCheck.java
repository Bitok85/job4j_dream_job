package ru.job4j.dream.utils;

import org.springframework.ui.Model;
import ru.job4j.dream.model.User;

import javax.servlet.http.HttpSession;

public final class UserCheck {

    private UserCheck() {
    }

    public static void checkForGuestOrExisting(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
    }
}
