package com.example.constructions.Controllers;

import com.example.constructions.Models.Account;
import com.example.constructions.Models.Employee;
import com.example.constructions.Repositories.AccountRepository;
import com.example.constructions.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String main(Model model){
        Iterable<Account> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);
        return "hrd/accounts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id){
        Account current = accountRepository.findById(id).get();
        Employee employee = employeeRepository.findById(current.getOwner().getId()).get();
        employee.setAccount(null);
        accountRepository.delete(current);
        return "redirect:/accounts/";
    }
}
