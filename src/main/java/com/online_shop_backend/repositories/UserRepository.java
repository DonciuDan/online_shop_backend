package com.online_shop_backend.repositories;

import com.online_shop_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Repository este un obiect care se ocupa de DB
@Repository // marcheaza o interfata pt a putea fi folosita cu dependecy injection
//@Repository este o componenta pentru care Spring-ul ne genereaza un Bean
public interface UserRepository extends JpaRepository<User,Integer> { // primeste User in cazul asta si intinger ca asa e id-ul
    Optional<User> findUserByUsername(String username);
    //ca nu e find all nu trebuie sa folosesti optional
    //daca e un user ar fi bine sa folosesti un optional ca sa nu dai de erori de genul NullPointerException



}
