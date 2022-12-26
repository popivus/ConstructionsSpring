package com.example.constructions.Controllers;

import com.example.constructions.Models.Account;
import com.example.constructions.Models.Employee;
import com.example.constructions.Models.Role;
import com.example.constructions.Repositories.AccountRepository;
import com.example.constructions.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/registration")
    public String registration(Model model){
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Account account, Model model, @RequestParam(name = "employee") Long id){
        Account userFromDb = accountRepository.findAccountByLogin(account.getLogin());
        if (userFromDb != null){
            Iterable<Employee> employees = employeeRepository.findAll();
            model.addAttribute("employees", employees);
            model.addAttribute("message", "Пользователь с таким логином уже зарегистрирован");
            return "registration";
        }
        account.setRoles(Collections.singleton(Role.USER));
        account.setOwner(employeeRepository.findById(id).get());
        accountRepository.save(account);
        return "redirect:/login";

    }

}