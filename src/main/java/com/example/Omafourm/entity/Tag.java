package com.example.Omafourm.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @param: Tag
 * @package: com.example.Omafourm.entity
 * @className: Tag
 * @description: TODO

 */
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

}
