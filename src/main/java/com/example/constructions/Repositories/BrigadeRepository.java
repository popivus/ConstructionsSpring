package com.example.constructions.Repositories;

import com.example.constructions.Models.Brigade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrigadeRepository extends CrudRepository<Brigade, Long> {
    List<Brigade> findByNameContaining(String name);
}
