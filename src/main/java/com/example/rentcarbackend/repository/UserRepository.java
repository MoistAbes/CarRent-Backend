package com.example.rentcarbackend.repository;

import com.example.rentcarbackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    User save(User user);

}
