package com.example.constructions.Repositories;

import com.example.constructions.Models.Sector;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SectorRepository extends CrudRepository<Sector, Long> {
    List<Sector> findByCityContaining(String city);
}
