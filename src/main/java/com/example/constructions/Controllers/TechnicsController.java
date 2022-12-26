package com.example.constructions.Controllers;

import com.example.constructions.Models.Country;
import com.example.constructions.Models.Employee;
import com.example.constructions.Models.Position;
import com.example.constructions.Models.Technics;
import com.example.constructions.Repositories.CountryRepository;
import com.example.constructions.Repositories.TechnicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/technics")
public class TechnicsController {
    @Autowired
    private TechnicsRepository technicsRepository;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public String main(Model model){
        Iterable<Technics> technics = technicsRepository.findAll();
        model.addAttribute("technics", technics);
        return "resources/technics";
    }

    @PostMapping("/")
    public String searchMain(Model model, @RequestParam String name){
        Iterable<Technics> technics;
        if (name != "" && name != null) technics = technicsRepository.findByNameContaining(name);
        else technics = technicsRepository.findAll();
        model.addAttribute("technics", technics);
        return "resources/technics";
    }

    @GetMapping("/{id}")
    public String pageView(@PathVariable(name = "id") Long id, Model model){
        Optional<Technics> tech = technicsRepository.findById(id);
        ArrayList<Technics> result = new ArrayList<>();
        tech.ifPresent(result::add);
        model.addAttribute("result", result);
        return "resources/technics_about";
    }

    @GetMapping("/delete/{id}")
    public String editView(@PathVariable(name = "id") Long id, Model model){
        Technics current = technicsRepository.findById(id).get();
        technicsRepository.delete(current);
        return "redirect:/technics/";
    }

    @GetMapping("/add")
    public String addView( Technics technics, Model model){
        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "resources/add_technics";
    }


    @PostMapping("/add")
    public String add(@Valid Technics technics, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Iterable<Country> countries = countryRepository.findAll();
            model.addAttribute("countries", countries);
            return "resources/add_technics";
        }
        technicsRepository.save(technics);
        return "redirect:/technics/";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") Long id, Technics technics, Model model){
        Technics current = technicsRepository.findById(id).get();
        technics.setName(current.getName());
        technics.setCountry(current.getCountry());
        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "resources/edit_technics";
    }


    @PostMapping("/edit/{id}")
    public String edit(@Valid Technics technics, BindingResult bindingResult, @PathVariable(name = "id") Long id, Model model){
        if (bindingResult.hasErrors()){
            Iterable<Country> countries = countryRepository.findAll();
            model.addAttribute("countries", countries);
            return "resources/edit_technics";
        }
        technics.setId(id);
        technicsRepository.save(technics);
        return "redirect:/technics/";
    }
}
