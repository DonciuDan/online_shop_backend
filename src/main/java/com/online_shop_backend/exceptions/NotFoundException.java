package com.online_shop_backend.exceptions;

public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }
    //cand user va fi null o sa apelam aceasta exceptie
    //in controller o sa tratam aceasta exceptie
}
