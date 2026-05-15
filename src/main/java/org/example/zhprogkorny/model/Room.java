package org.example.zhprogkorny.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Plant> plants;

    // --- Getterek és Setterek ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Plant> getPlants() { return plants; }
    public void setPlants(List<Plant> plants) { this.plants = plants; }
}