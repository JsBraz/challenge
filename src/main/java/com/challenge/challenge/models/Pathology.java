package com.challenge.challenge.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pathology")
public class Pathology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "symptom_id")
    private List<Symptom> symptom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Symptom> getSymptom() {
        return symptom;
    }

    public void setSymptom(List<Symptom> symptom) {
        this.symptom = symptom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
