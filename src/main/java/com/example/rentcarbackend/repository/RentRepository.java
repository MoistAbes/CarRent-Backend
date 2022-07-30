package com.example.rentcarbackend.repository;

import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {

    @Override
    List<Rent> findAll();

    @Override
    Optional<Rent> findById(Long id);

    @Override
    Rent save(Rent rent);

    List<Rent> findAllByUserId(Long userId);

}
