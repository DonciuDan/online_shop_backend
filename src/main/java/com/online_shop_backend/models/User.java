package com.online_shop_backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING) //salveaza valoarea din enum ca si text in db
    private UserRole userRole;
}
