package org.example.zhprogkorny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class WateringLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int amountMl;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    @JsonIgnore
    private Plant plant;

    // --- Getterek és Setterek ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public int getAmountMl() { return amountMl; }
    public void setAmountMl(int amountMl) { this.amountMl = amountMl; }
    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) { this.plant = plant; }
}