package com.example.constructions.Controllers;

import com.example.constructions.Models.Employee;
import com.example.constructions.Models.Position;
import com.example.constructions.Repositories.EmployeeRepository;
import com.example.constructions.Repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String main(Model model){
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("positionArray", positions);
        return "hrd/positions";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") Long id, Position position, Model model){
        Position current = positionRepository.findById(id).get();
        position.setName(current.getName());
        position.setSalary(current.getSalary());
        return "hrd/edit_position";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Position position, BindingResult bindingResult, @PathVariable(name = "id") Long id, Model model){
        if (bindingResult.hasErrors()){
            return "hrd/edit_position";
        }
        position.setId(id);
        positionRepository.save(position);
        return "redirect:/positions/";
    }

    @GetMapping("/add")
    public String addView( Position position, Model model){
        return "hrd/add_position";
    }


    @PostMapping("/add")
    public String add(@Valid Position position, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "hrd/add_position";
        }
        positionRepository.save(position);
        return "redirect:/positions/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Model model){
        Position current = positionRepository.findById(id).get();
        Iterable<Employee> employees = employeeRepository.findAll();
        for (Employee emp:
             employees) {
            if (emp.getPosition() == current){
                emp.setPosition(null);
                employeeRepository.save(emp);
            }
        }
        positionRepository.delete(current);
        return "redirect:/positions/";
    }
}
