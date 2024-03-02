package com.online_shop_backend.services.impl;

import com.online_shop_backend.exceptions.InvalidDataException;
import com.online_shop_backend.exceptions.NotFoundException;
import com.online_shop_backend.models.User;
import com.online_shop_backend.repositories.UserRepository;
import com.online_shop_backend.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService { // de ce impartim asa clasele si interfetele? pentru ca e
    // mai usor de umblat prin ele, pentru ca putem face abstractizarea si pentru ca daca
    // schimbam de ex: din my sql in mongoDB schimbam codul doar in repository si userServiceimpl

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) throws NotFoundException {
//        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
//        return user;
//    }
        //mai sus foloseste lamba expression
        //mai jos e scris cu Optional ca sa am ambele variante
        //poti face si direct cu return la varianta de sus

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userOptional.get();
    }

    @Override
    public User findByUsername(String username) throws NotFoundException, InvalidDataException {
        //.isEmpty() verifica ca textul sa aiba o lungime mai mare ca 0
        //.isBlank() verifica ca textul sa nu contina doar spatii, " "
        if (username == null || username.isEmpty() || username.isBlank()) {
            throw new InvalidDataException("Username is invalid");
        }
        return userRepository
                .findUserByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException("User wasn't found"));
    }

    @Override
    public User create(User user) throws InvalidDataException {
        validateUser(user);
        //daca se ajunge la primul throw nu s-a va mai merge mai departe
        //si prinln in cazul de mai jos nu va mai fi folosit
        //System.out.println();
        //daca toate campurile din user sunt valide iar metoda nu a aruncat nici o exceptie
        //inseamna ca obiectul se poate salva in DB

        return userRepository.save(user);
    }

    @Override
    public User update(User user) throws NotFoundException, InvalidDataException {
        validateUser(user);
        if (user.getId() == null) {
            throw new InvalidDataException("The id is invalid");
        }
        //verificam daca ID-ul este null aici si nu in validateUser fiindca
        // trebuie sa fie creat obiectul(userul) mai intai ca sa il putem verifica
        findById(user.getId()); //metoda find by id verifica daca exista utilizatorul in baza de date, daca nu exista arunca exceptia
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        findById(id); //metoda find by id verifica daca exista utilizatorul in baza de date, daca nu exista arunca exceptia
        this.userRepository.deleteById(id);
    }

    //    private void test(){
//        try {
//            validateUser(null);
//        } catch (InvalidDataException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private void validateUser(User user) throws InvalidDataException {
        if (user == null) {
            throw new InvalidDataException("All the data are invalid");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty() || user.getUsername().isBlank()) {
            throw new InvalidDataException("The Username is invalid");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().isBlank()) {
            throw new InvalidDataException("The Email is invalid");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank()) {
            throw new InvalidDataException("The Password is invalid");
        }
        if (user.getUserRole() == null) {
            throw new InvalidDataException("The Role is invalid");
        }
        //TODO: de verificat daca rolul trimis face parte din ENUM
    }
}
