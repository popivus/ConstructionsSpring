package com.example.constructions.Controllers;

import com.example.constructions.Models.Country;
import com.example.constructions.Models.Material;
import com.example.constructions.Models.Technics;
import com.example.constructions.Repositories.MaterialRepository;
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
@RequestMapping("/materials")
public class MaterialsController {
    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping
    public String main(Model model){
        Iterable<Material> materials = materialRepository.findAll();
        model.addAttribute("materials", materials);
        return "resources/materials";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") Long id, Material material, Model model){
        Material current = materialRepository.findById(id).get();
        material.setName(current.getName());
        material.setAmount(current.getAmount());
        return "resources/edit_material";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Material material, BindingResult bindingResult, @PathVariable(name = "id") Long id, Model model){
        if (bindingResult.hasErrors()){
            return "resources/edit_material";
        }
        material.setId(id);
        materialRepository.save(material);
        return "redirect:/materials/";
    }

    @GetMapping("/add")
    public String addView( Material material, Model model){
        return "resources/add_material";
    }


    @PostMapping("/add")
    public String add(@Valid Material material, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "resources/add_material";
        }
        materialRepository.save(material);
        return "redirect:/materials/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Model model){
        Material current = materialRepository.findById(id).get();
        materialRepository.delete(current);
        return "redirect:/materials/";
    }
}
