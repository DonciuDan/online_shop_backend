package com.online_shop_backend.exceptions;

/**
 * folosim aceasta exceptie pentru cazurile in care parametri sau body-ul Requestului sunt invalide
 * spre exemplu Username este null some empty
 */
public class InvalidDataException extends Exception{
    public InvalidDataException(String message) {
        super(message);
    }
}
