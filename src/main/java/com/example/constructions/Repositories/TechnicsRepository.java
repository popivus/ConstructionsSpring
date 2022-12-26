package com.example.constructions.Repositories;

import com.example.constructions.Models.Technics;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TechnicsRepository extends CrudRepository<Technics, Long> {
    List<Technics> findByNameContaining(String name);
}
