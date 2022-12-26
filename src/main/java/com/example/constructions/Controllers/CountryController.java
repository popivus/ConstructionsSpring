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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TechnicsRepository technicsRepository;

    @GetMapping
    public String main(Model model){
        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "resources/countries";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") Long id, Country country, Model model){
        Country current = countryRepository.findById(id).get();
        country.setName(current.getName());
        return "resources/edit_country";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Country country, BindingResult bindingResult, @PathVariable(name = "id") Long id, Model model){
        if (bindingResult.hasErrors()){
            return "resources/edit_country";
        }
        country.setId(id);
        countryRepository.save(country);
        return "redirect:/countries/";
    }

    @GetMapping("/add")
    public String addView( Country country, Model model){
        return "resources/add_country";
    }


    @PostMapping("/add")
    public String add(@Valid Country country, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "resources/add_country";
        }
        countryRepository.save(country);
        return "redirect:/countries/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Model model){
        Country current = countryRepository.findById(id).get();
        Iterable<Technics> technics = technicsRepository.findAll();
        for (Technics tech:
                technics) {
            if (tech.getCountry() == current){
                tech.setCountry(null);
                technicsRepository.save(tech);
            }
        }
        countryRepository.delete(current);
        return "redirect:/countries/";
    }
}
