package com.example.constructions.Controllers;

import com.example.constructions.Models.*;
import com.example.constructions.Models.Object;
import com.example.constructions.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping
public class ObjectController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private Materials_ObjectRepository materialsObjectRepository;

    @Autowired
    private TechnicsRepository technicsRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @GetMapping
    public String main(Model model){
        Iterable<Object> objects = objectRepository.findAll();
        model.addAttribute("objects", objects);
        return "object/objects";
    }

    @PostMapping
    public String searchMain(Model model, @RequestParam String city){
        Iterable<Object> objects;
        if (city != "" && city != null) objects = objectRepository.findBySectorCity(city);
        else objects = objectRepository.findAll();
        model.addAttribute("objects", objects);
        return "object/objects";
    }

    @GetMapping("/show/{id}")
    public String pageView(@PathVariable(name = "id") Long id, Model model){
        Optional<Object> object = objectRepository.findById(id);
        ArrayList<Object> result = new ArrayList<>();
        object.ifPresent(result::add);
        model.addAttribute("result", result);
        Iterable<Material> materials = materialRepository.findAll();
        Iterable<Technics> technics = technicsRepository.findAll();
        model.addAttribute("materials", materials);
        model.addAttribute("technics", technics);
        return "object/object_about";
    }

    @PostMapping("/add_tech/{id}")
    public String addTech(@PathVariable(name = "id") Long id, @RequestParam(name = "technic") Long empId, Model model){
        Technics technic = technicsRepository.findById(empId).get();
        Object current = objectRepository.findById(id).get();
        current.getTechnics().add(technic);
        objectRepository.save(current);
        Optional<Object> object = objectRepository.findById(id);
        ArrayList<Object> result = new ArrayList<>();
        object.ifPresent(result::add);
        model.addAttribute("result", result);
        Iterable<Material> materials = materialRepository.findAll();
        Iterable<Technics> technics = technicsRepository.findAll();
        model.addAttribute("materials", materials);
        model.addAttribute("technics", technics);
        return "redirect:/show/" + id.toString();
    }

    @PostMapping("/add_mat/{id}")
    public String addMaterial(@PathVariable(name = "id") Long id, @RequestParam(name = "material") Long empId, @RequestParam(name = "amount") int amount, Model model){
        Material material = materialRepository.findById(empId).get();
        Object current = objectRepository.findById(id).get();
        Materials_Object materialsObject = new Materials_Object();
        materialsObject.setMaterial(material);
        materialsObject.setObject(current);
        materialsObject.setAmount(amount);
        materialsObjectRepository.save(materialsObject);
        Optional<Object> object = objectRepository.findById(id);
        ArrayList<Object> result = new ArrayList<>();
        object.ifPresent(result::add);
        model.addAttribute("result", result);
        Iterable<Material> materials = materialRepository.findAll();
        Iterable<Technics> technics = technicsRepository.findAll();
        model.addAttribute("materials", materials);
        model.addAttribute("technics", technics);
        return "redirect:/show/" + id.toString();
    }

    @GetMapping("/add")
    public String addView(Object object, Model model){
        Iterable<Sector> sectors = sectorRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("sectors", sectors);
        model.addAttribute("employees", employees);
        return "object/add_object";
    }

    @PostMapping("/add")
    public String add(@Valid Object object, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Iterable<Sector> sectors = sectorRepository.findAll();
            Iterable<Employee> employees = employeeRepository.findAll();
            model.addAttribute("sectors", sectors);
            model.addAttribute("employees", employees);
            return "object/add_object";
        }
        objectRepository.save(object);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") Long id, Object object, Model model){
        Object current = objectRepository.findById(id).get();
        object.setArea(current.getArea());
        object.setObjectType(current.getObjectType());
        object.setBuildingNumber(current.getBuildingNumber());
        object.setBuildingPermit(current.getBuildingPermit());
        object.setEmployee(current.getEmployee());
        object.setFloors(current.getFloors());
        object.setSector(current.getSector());
        object.setStartDate(current.getStartDate());
        object.setEndDate(current.getEndDate());
        Iterable<Sector> sectors = sectorRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("sectors", sectors);
        model.addAttribute("employees", employees);
        return "object/edit_object";
    }


    @PostMapping("/edit/{id}")
    public String edit(@Valid Object object, BindingResult bindingResult, @PathVariable(name = "id") Long id, Model model){
        if (bindingResult.hasErrors()){
            Iterable<Sector> sectors = sectorRepository.findAll();
            Iterable<Employee> employees = employeeRepository.findAll();
            model.addAttribute("sectors", sectors);
            model.addAttribute("employees", employees);
            return "object/edit_object";
        }
        object.setId(id);
        objectRepository.save(object);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Model model){
        Object current = objectRepository.findById(id).get();
        objectRepository.delete(current);
        return "redirect:/";
    }

    @GetMapping("/{id}/remove_tech/{idEmp}")
    public String removeTech(Model model, @PathVariable(name = "idEmp") Long idEmp, @PathVariable(name = "id") Long idBr){
        Technics tech = technicsRepository.findById(idEmp).get();
        Object current = objectRepository.findById(idBr).get();
        current.getTechnics().remove(tech);
        objectRepository.save(current);
        return "redirect:/show/" + idBr.toString();
    }

    @GetMapping("/{id}/remove_mat/{idEmp}")
    public String removeMaterial(Model model, @PathVariable(name = "idEmp") Long idEmp, @PathVariable(name = "id") Long idBr){
        Materials_Object materialsObject = materialsObjectRepository.findById(idEmp).get();
        materialsObjectRepository.delete(materialsObject);
        return "redirect:/show/" + idBr.toString();
    }
}
