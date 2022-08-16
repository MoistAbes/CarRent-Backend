package com.example.rentcarbackend.repository;

import com.example.rentcarbackend.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(Long id);

    @Override
    Car save(Car car);

}
