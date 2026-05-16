package org.example.zhprogkorny.controller;

import org.example.zhprogkorny.model.Plant;
import org.example.zhprogkorny.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @PostMapping
    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant) {
        Plant savedPlant = plantService.savePlant(plant);
        return new ResponseEntity<>(savedPlant, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id) {
        Optional<Plant> plant = plantService.getPlantById(id);
        return plant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long id, @RequestBody Plant plantDetails) {
        Optional<Plant> existingPlant = plantService.getPlantById(id);

        if (existingPlant.isPresent()) {
            Plant updatedPlant = existingPlant.get();
            updatedPlant.setName(plantDetails.getName());
            updatedPlant.setWaterFrequencyDays(plantDetails.getWaterFrequencyDays());
            return ResponseEntity.ok(plantService.savePlant(updatedPlant));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        if (plantService.getPlantById(id).isPresent()) {
            plantService.deletePlant(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}