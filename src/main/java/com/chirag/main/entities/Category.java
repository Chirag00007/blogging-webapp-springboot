package com.chirag.main.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cId")
    private Integer cId;
    private String cTitle;
    private String cDescription;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY ,mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

}
