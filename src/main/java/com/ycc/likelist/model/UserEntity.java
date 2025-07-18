package com.esun.productpreference.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User")
@Data
public class User {

    @Id
    private String userID;

    private String userName;

    private String email;

    private String account;
}
