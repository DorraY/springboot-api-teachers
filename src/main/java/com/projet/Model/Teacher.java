package com.projet.Model;


import javax.persistence.*;

@Entity
public class Teacher {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;
    private String name;
    private String lastname;
    private String specialty;
    @ManyToOne
    private Section section;



    public Teacher(int id, String name, String lastname, Section section) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.section = section;
    }

    public Teacher() {

    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
