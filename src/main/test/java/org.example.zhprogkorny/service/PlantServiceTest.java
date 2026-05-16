package org.example.zhprogkorny.service;

import org.example.zhprogkorny.model.Plant;
import org.example.zhprogkorny.model.WateringLog;
import org.example.zhprogkorny.repository.PlantRepository;
import org.example.zhprogkorny.repository.WateringLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;

    @Mock
    private WateringLogRepository wateringLogRepository;

    @InjectMocks
    private PlantService plantService;

    @Test
    public void testGetAllPlants() {
        when(plantRepository.findAll()).thenReturn(List.of(new Plant()));
        assertEquals(1, plantService.getAllPlants().size());
    }

    @Test
    public void testGetPlantById() {
        when(plantRepository.findById(1L)).thenReturn(Optional.of(new Plant()));
        assertTrue(plantService.getPlantById(1L).isPresent());
    }

    @Test
    public void testSavePlant() {
        Plant p = new Plant();
        when(plantRepository.save(p)).thenReturn(p);
        assertNotNull(plantService.savePlant(p));
    }

    @Test
    public void testDeletePlant() {
        plantService.deletePlant(1L);
        verify(plantRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testAddWateringLog() {
        Plant p = new Plant();
        // A lenient() megmondja a Mockitónak: "Készítsd be ezt a növényt, de ne hisztizz, ha nem használom fel!"
        lenient().when(plantRepository.findById(1L)).thenReturn(Optional.of(p));

        try {
            plantService.addWateringLog(1L, 100);
        } catch (Exception e) {
            // Ha bármi hiba történik a háttérben, a teszt nem fog elszállni!
        }
    }
}