package com.chirag.main.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "user_name", nullable = false, length = 100)
    private String name;
    @Column(name = "user_email", nullable = false, length = 100)
    private String email;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_about", length = 100)
    private String about;

}
