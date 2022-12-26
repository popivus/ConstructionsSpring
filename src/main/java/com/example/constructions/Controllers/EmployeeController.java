package com.example.constructions.Controllers;

import com.example.constructions.Models.Employee;
import com.example.constructions.Models.Position;
import com.example.constructions.Repositories.EmployeeRepository;
import com.example.constructions.Repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String main(Model model){
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employeeArray", employees);
        return "hrd/employees";
    }

    @PostMapping("/")
    public String searchMain(Model model, @RequestParam String surname){
        Iterable<Employee> employees;
        if (surname != "" && surname != null) employees = employeeRepository.findBySurnameContains(surname);
        else employees = employeeRepository.findAll();
        model.addAttribute("employeeArray", employees);
        return "hrd/employees";
    }

    @GetMapping("/{id}")
    public String pageView(@PathVariable(name = "id") Long id, Model model){
        Optional<Employee> employee = employeeRepository.findById(id);
        ArrayList<Employee> result = new ArrayList<>();
        employee.ifPresent(result::add);
        model.addAttribute("result", result);
        return "hrd/employees_about";
    }

    @GetMapping("/delete/{id}")
    public String editView(@PathVariable(name = "id") Long id, Model model){
        Employee current = employeeRepository.findById(id).get();
        employeeRepository.delete(current);
        return "redirect:/employees/";
    }

    @GetMapping("/add")
    public String addView( Employee employee, Model model){
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        return "hrd/add_employee";
    }


    @PostMapping("/add")
    public String add(@Valid Employee employee, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Iterable<Position> positions = positionRepository.findAll();
            model.addAttribute("positions", positions);
            return "hrd/add_employee";
        }
        employeeRepository.save(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") Long id, Employee employee, Model model){
        Employee current = employeeRepository.findById(id).get();
        employee.setSurname(current.getSurname());
        employee.setName(current.getName());
        employee.setPosition(current.getPosition());
        employee.setBirthday(current.getBirthday());
        employee.setMiddlename(current.getMiddlename());
        employee.setFired(current.getFired());
        employee.setPassportSeria(current.getPassportSeria());
        employee.setPassportNumber(current.getPassportNumber());
        employee.setINN(current.getINN());
        employee.setSNILS(current.getSNILS());
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        return "hrd/edit_employee";
    }


    @PostMapping("/edit/{id}")
    public String edit(@Valid Employee employee, BindingResult bindingResult, @PathVariable(name = "id") Long id, Model model){
        if (bindingResult.hasErrors()){
            Iterable<Position> positions = positionRepository.findAll();
            model.addAttribute("positions", positions);
            return "hrd/edit_employee";
        }
        employee.setId(id);
        employeeRepository.save(employee);
        return "redirect:/employees/";
    }
}
