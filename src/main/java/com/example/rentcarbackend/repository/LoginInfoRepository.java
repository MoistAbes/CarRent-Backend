package com.example.rentcarbackend.repository;

import com.example.rentcarbackend.entity.LoginInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface LoginInfoRepository extends CrudRepository<LoginInfo, Long> {
    @Override
    List<LoginInfo> findAll();

    List<LoginInfo> findAllByUserId(Long id);

    @Override
    Optional<LoginInfo> findById(Long id);

    @Override
    LoginInfo save(LoginInfo loginInfo);



}
