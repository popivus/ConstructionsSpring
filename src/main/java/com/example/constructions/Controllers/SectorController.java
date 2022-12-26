package com.example.constructions.Controllers;

import com.example.constructions.Models.Sector;
import com.example.constructions.Repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/sectors")
public class SectorController {
    @Autowired
    private SectorRepository sectorRepository;

    @GetMapping
    public String main(Model model){
        Iterable<Sector> sectors = sectorRepository.findAll();
        model.addAttribute("sectors", sectors);
        return "sector/sectors";
    }

    @PostMapping
    public String searchMain(Model model, @RequestParam String city){
        Iterable<Sector> sectors;
        if (city != "" && city != null) sectors = sectorRepository.findByCityContaining(city);
        else sectors = sectorRepository.findAll();
        model.addAttribute("sectors", sectors);
        return "sector/sectors";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") Long id, Sector sector, Model model){
        Sector current = sectorRepository.findById(id).get();
        sector.setCountry(current.getCountry());
        sector.setCity(current.getCity());
        sector.setStreet(current.getStreet());
        sector.setArea(current.getArea());
        return "sector/edit_sector";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Sector sector, BindingResult bindingResult, @PathVariable(name = "id") Long id, Model model){
        if (bindingResult.hasErrors()){
            return "sector/edit_sector";
        }
        sector.setId(id);
        sectorRepository.save(sector);
        return "redirect:/sectors/";
    }

    @GetMapping("/add")
    public String addView( Sector sector, Model model){
        return "sector/add_sector";
    }


    @PostMapping("/add")
    public String add(@Valid Sector sector, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "sector/add_sector";
        }
        sectorRepository.save(sector);
        return "redirect:/sectors/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Model model){
        Sector current = sectorRepository.findById(id).get();
        sectorRepository.delete(current);
        return "redirect:/sectors/";
    }
}
