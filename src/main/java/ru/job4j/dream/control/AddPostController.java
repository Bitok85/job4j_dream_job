package ru.job4j.dream.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dream.service.CityService;

@Controller
public class AddPostController {

    private CityService cityService;

    public AddPostController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping ("/addPost")
    public String addPost(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "addPost";
    }
}
