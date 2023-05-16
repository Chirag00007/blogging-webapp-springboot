package com.chirag.main.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.singleton;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User{

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


}
