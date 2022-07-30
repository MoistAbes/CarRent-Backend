package com.example.rentcarbackend.service;

import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.exception.RentNotFoundException;
import com.example.rentcarbackend.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentDbService {

    private final RentRepository repository;

    public List<Rent> getAllRents(){
        return repository.findAll();
    }

    public List<Rent> getAllUserRents(final Long userId){
        return repository.findAllByUserId(userId);
    }

    public Rent getRent(final Long rentId) throws RentNotFoundException {
        return repository.findById(rentId).orElseThrow(RentNotFoundException::new);
    }

    public Rent saveRent(final Rent rent){
        return repository.save(rent);
    }

    public void deleteRent(Long rentId){
         repository.deleteById(rentId);
    }

}
