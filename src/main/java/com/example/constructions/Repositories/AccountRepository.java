package com.example.constructions.Repositories;

import com.example.constructions.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByLogin(String login);
}
