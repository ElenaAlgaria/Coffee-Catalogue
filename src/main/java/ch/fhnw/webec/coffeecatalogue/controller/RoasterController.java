package ch.fhnw.webec.coffeecatalogue.controller;

import ch.fhnw.webec.coffeecatalogue.repository.RoasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoasterController {
    @Autowired
    private RoasterRepository roasterRepository;

    @RequestMapping(path = "/bean/roaster", method = RequestMethod.GET)
    public String showRoaster(Model model) {
        model.addAttribute("roasterList", this.roasterRepository.findAll());

        return "bean/roaster";
    }
}
