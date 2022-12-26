package com.example.constructions.Controllers;

import com.example.constructions.Models.*;
import com.example.constructions.Models.Object;
import com.example.constructions.Repositories.BrigadeRepository;
import com.example.constructions.Repositories.EmployeeRepository;
import com.example.constructions.Repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/brigades")
public class BrigadeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BrigadeRepository brigadeRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @GetMapping
    public String main(Model model){
        Iterable<Brigade> brigades = brigadeRepository.findAll();
        model.addAttribute("brigades", brigades);
        return "brigade/brigades";
    }

    @PostMapping
    public String searchMain(Model model, @RequestParam String name){
        Iterable<Brigade> brigades;
        if (name != "" && name != null) brigades = brigadeRepository.findByNameContaining(name);
        else brigades = brigadeRepository.findAll();
        model.addAttribute("brigades", brigades);
        return "brigade/brigades";
    }

    @GetMapping("/{id}")
    public String pageView(@PathVariable(name = "id") Long id, Model model){
        Optional<Brigade> brigade = brigadeRepository.findById(id);
        ArrayList<Brigade> result = new ArrayList<>();
        brigade.ifPresent(result::add);
        model.addAttribute("result", result);
        Iterable<Employee> employees = employeeRepository.findAll();

        model.addAttribute("employees", employees);
        return "brigade/brigade_about";
    }

    @PostMapping("/{id}")
    public String addEmployee(@PathVariable(name = "id") Long id, @RequestParam(name = "employee") Long empId, Model model){
        Employee employee = employeeRepository.findById(empId).get();
        Brigade current = brigadeRepository.findById(id).get();
        current.getEmployees().add(employee);
        brigadeRepository.save(current);
        Optional<Brigade> brigade = brigadeRepository.findById(id);
        ArrayList<Brigade> result = new ArrayList<>();
        brigade.ifPresent(result::add);
        model.addAttribute("result", result);
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "brigade/brigade_about";
    }

    @GetMapping("/add")
    public String addView(Brigade brigade, Model model){
        Iterable<Object> objects = objectRepository.findAll();
        model.addAttribute("objects", objects);
        return "brigade/add_brigade";
    }

    @PostMapping("/add")
    public String add(@Valid Brigade brigade, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Iterable<Object> objects = objectRepository.findAll();
            model.addAttribute("objects", objects);
            return "brigade/add_brigade";
        }
        brigadeRepository.save(brigade);
        return "redirect:/brigades/";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") Long id, Brigade brigade, Model model){
        Brigade current = brigadeRepository.findById(id).get();
        brigade.setName(current.getName());
        brigade.setObject(current.getObject());
        Iterable<Object> objects = objectRepository.findAll();
        model.addAttribute("objects", objects);
        return "brigade/edit_brigade";
    }


    @PostMapping("/edit/{id}")
    public String edit(@Valid Brigade brigade, BindingResult bindingResult, @PathVariable(name = "id") Long id, Model model){
        if (bindingResult.hasErrors()){
            Iterable<Object> objects = objectRepository.findAll();
            model.addAttribute("objects", objects);
            return "brigade/edit_brigade";
        }
        brigade.setId(id);
        brigadeRepository.save(brigade);
        return "redirect:/brigades/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Model model){
        Brigade current = brigadeRepository.findById(id).get();
        brigadeRepository.delete(current);
        return "redirect:/brigades/";
    }

    @GetMapping("/{id}/remove/{idEmp}")
    public String removeEmployee(Model model, @PathVariable(name = "idEmp") Long idEmp, @PathVariable(name = "id") Long idBr){
        Employee employee = employeeRepository.findById(idEmp).get();
        Brigade current = brigadeRepository.findById(idBr).get();
        current.getEmployees().remove(employee);
        brigadeRepository.save(current);
        return "redirect:/brigades/" + idBr.toString();
    }
}
