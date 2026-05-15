package org.example.zhprogkorny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int waterFrequencyDays;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    private List<WateringLog> wateringLogs;

    // --- Getterek és Setterek ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getWaterFrequencyDays() { return waterFrequencyDays; }
    public void setWaterFrequencyDays(int waterFrequencyDays) { this.waterFrequencyDays = waterFrequencyDays; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public List<WateringLog> getWateringLogs() { return wateringLogs; }
    public void setWateringLogs(List<WateringLog> wateringLogs) { this.wateringLogs = wateringLogs; }
}