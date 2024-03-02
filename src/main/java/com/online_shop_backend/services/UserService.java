package com.online_shop_backend.services;

import com.online_shop_backend.exceptions.InvalidDataException;
import com.online_shop_backend.exceptions.NotFoundException;
import com.online_shop_backend.models.User;
import org.aspectj.weaver.ast.Not;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //READ
    List<User> findAll();
    User findById(Integer id) throws NotFoundException;
    //diferenta intre throws si throw
    // verifici daca user este null sau nu
    //controler intermedierea intre Service si
    //in Service o sa avem toata logica

    User findByUsername(String username) throws NotFoundException, InvalidDataException;
    //CREATE
    User create(User user) throws InvalidDataException;
    //UPDATE
    User update(User user) throws NotFoundException, InvalidDataException;
    //DELETE
    void delete(Integer id) throws NotFoundException;

}
