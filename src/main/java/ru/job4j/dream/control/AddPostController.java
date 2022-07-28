package ru.job4j.dream.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;

@Controller
public class AddPostController {

    @GetMapping ("/addPost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post(0, "PHP", "some text", LocalDateTime.now()));
        return "addPost";
    }
}