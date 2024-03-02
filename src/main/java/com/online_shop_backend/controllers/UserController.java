package com.online_shop_backend.controllers;

import com.online_shop_backend.exceptions.InvalidDataException;
import com.online_shop_backend.exceptions.NotFoundException;
import com.online_shop_backend.models.User;
import com.online_shop_backend.services.UserService;
import com.online_shop_backend.utils.ApiResponse;
import com.online_shop_backend.utils.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
//@RequestMapping e precum adresa unui bloc, cum poti ajunge acolo
//@GetMapping este practic drumul de la intrarea blocului pana la apartament
//RequestMapping se foloseste cand ai acelasi link (ruta comuna) la toate metodele poti pune direct in requestMapping
public class UserController {
    //@GetMapping("/users")
    //GetMapping se foloseste ca sa pui la fiecare metoda adresa
//    public String hello (){
//        return "Hello";
//    }

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody User user) {
//        try {
//            return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
//        } catch (InvalidDataException e) {
//            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
//        }
        try {
            User result = userService.create(user);
            return ResponseHandler.createResponse(HttpStatus.OK,"User created", result);
            //Poti face ca mai jos (clasic) sau implementezi metoda ca mai sus
//            ApiResponse response = ApiResponse.builder()
//                    .status(HttpStatus.OK)
//                    .message("User created with success")
//                    .data(result)
//                    .build();
//            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (InvalidDataException e) {
            return ResponseHandler.createResponse(HttpStatus.BAD_REQUEST,e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id){
        try {
            userService.delete(id);
            return ResponseHandler.createResponse(HttpStatus.OK,"User deleted", null);
        } catch (NotFoundException e){
            return ResponseHandler.createResponse(HttpStatus.BAD_REQUEST,e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(){
        List<User> result =  userService.findAll();
        return ResponseHandler.createResponse(HttpStatus.OK,"Users List",result);
    }

}
