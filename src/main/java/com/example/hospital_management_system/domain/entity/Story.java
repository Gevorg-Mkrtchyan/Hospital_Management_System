package com.example.hospital_management_system.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "recipe")
    private String recipe;
    @ManyToOne
    @JoinColumn(name = "registration_id", foreignKey = @ForeignKey(name = "story_registration_fk"))
    private Registration registration;

    public Story() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
