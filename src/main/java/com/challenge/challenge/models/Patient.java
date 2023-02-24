package com.challenge.challenge.models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;

    @OneToMany
    @JoinColumn(name = "pathology_id")
    private List<Pathology> pathology;

    public Patient(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Patient() {
        // Default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Pathology> getPathology() {
        return pathology;
    }

    public void setPathology(List<Pathology> pathology) {
        this.pathology = pathology;
    }


}
