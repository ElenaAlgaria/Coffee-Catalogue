package ch.fhnw.webec.coffeecatalogue.controller;

import ch.fhnw.webec.coffeecatalogue.model.Bean;
import ch.fhnw.webec.coffeecatalogue.repository.BeansRepository;
import ch.fhnw.webec.coffeecatalogue.repository.RoasterRepository;
import ch.fhnw.webec.coffeecatalogue.repository.RoastingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class BeansController {

    @Autowired
    private BeansRepository beansRepository;

    @Autowired
    private RoastingRepository roastingRepository;

    @Autowired
    private RoasterRepository roasterRepository;

    private final static String DEFAULT_IMAGE = "/images/stockImages/default_bean.jpg";

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(@RequestParam() Optional<String> search, Model model) {
        model.addAttribute("search", search.orElse(""));
        model.addAttribute("beansList", this.beansRepository.findBySearch(search.orElse("")));

        return "bean/index";
    }

    @RequestMapping(path = "/bean/{id}", method = RequestMethod.GET)
    public String showBeans(@PathVariable long id, Model model) {
        model.addAttribute("beanDetail",
            this.beansRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        return "bean/show";
    }

    @RequestMapping(path = "/bean/add", method = RequestMethod.GET)
    public String addBeans(Model model) {
        model.addAttribute("roastings", this.roastingRepository.findAll());
        model.addAttribute("roasters", this.roasterRepository.findAll());

        return "bean/add-or-edit";
    }

    @RequestMapping(path = "/bean/add", method = RequestMethod.POST)
    public String addBeans(@Valid Bean beans, BindingResult bindingResult, Model model) {
        checkImageUrl(beans, bindingResult);

        return checkingToSave(beans, bindingResult, model);
    }

    @RequestMapping(path = "/bean/{id}/edit", method = RequestMethod.GET)
    public String editBeans(@PathVariable long id, Model model) {
        model.addAttribute("roastings", this.roastingRepository.findAll());
        model.addAttribute("roasters", this.roasterRepository.findAll());
        model.addAttribute("beanBag",
            this.beansRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        return "bean/add-or-edit";
    }

    @RequestMapping(path = "/bean/{id}/edit", method = RequestMethod.POST)
    public String editBeans(@Valid Bean beans, BindingResult bindingResult, Model model) {
        checkImageUrl(beans, bindingResult);

        return checkingToSave(beans, bindingResult, model);
    }

    @RequestMapping(path = "/bean/{id}/delete", method = RequestMethod.POST)
    public String deleteBeans(@PathVariable long id) {
        this.beansRepository.delete(
            this.beansRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        return "redirect:/";
    }

    @RequestMapping(path = "/bean/{id}/fav/{fav}", method = RequestMethod.POST)
    public String addAsFavorite(@PathVariable long id, @PathVariable String fav) {
        if (!List.of("index", "show").contains(fav)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Bean beans =
            this.beansRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        beans.setFavorite(!beans.getFavorite());

        this.beansRepository.save(beans);

        if (fav.equals("show")) {
            return "redirect:/bean/" + beans.getId();
        } else {
            return "redirect:/";
        }
    }

    private void checkImageUrl(Bean beans, BindingResult bindingResult) {
        if (!(beans.getImagePath().equals(DEFAULT_IMAGE)
            || beans.getImagePath()
                    .matches("^(http(s?):)([/|.\\w\\s\\-])*\\.(?:jpg|gif|png|svg|jpeg)([/|.\\w\\s\\-?=])*$"))) {
            bindingResult.addError(
                new FieldError("bean", "imagePath", "has to be a full URL to an Image! Or use current default image."));
            beans.setImagePath(DEFAULT_IMAGE);
        }
    }

    private String checkingToSave(@Valid Bean beans, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roastings", this.roastingRepository.findAll());
            model.addAttribute("roasters", this.roasterRepository.findAll());
            model.addAttribute("beanBag", beans);

            return "bean/add-or-edit";
        } else {
            this.beansRepository.save(beans);

            return "redirect:/bean/" + beans.getId();
        }
    }
}