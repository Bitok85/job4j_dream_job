package ru.job4j.dream.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AddCandidateController {

    @GetMapping("/addCandidate")
    public String addCandidate(Model model) {
        return "addCandidate";
    }
}
