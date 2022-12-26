package com.example.constructions.Repositories;

import com.example.constructions.Models.Object;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ObjectRepository extends CrudRepository<Object, Long> {
    List<Object> findBySectorCity(String city);
}
