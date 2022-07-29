package ru.job4j.dream.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dream.model.Candidate;

import java.time.LocalDateTime;

@Controller
public class AddCandidateController {

    @GetMapping("/addCandidate")
    public String addCandidate(Model model) {
        model.addAttribute("candidate", new Candidate(0, "Sergey Kamushkin", "some text", LocalDateTime.now()));
        return "addCandidate";
    }
}
