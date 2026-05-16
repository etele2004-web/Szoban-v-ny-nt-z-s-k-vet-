package org.example.zhprogkorny.controller;

import org.example.zhprogkorny.model.Plant;
import org.example.zhprogkorny.model.WateringLog;
import org.example.zhprogkorny.service.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlantControllerTest {

    @Mock
    private PlantService plantService;

    private PlantController plantController;

    @BeforeEach
    public void setUp() {
        plantController = new PlantController(plantService);
    }

    @Test
    public void testGetAllPlants() {
        when(plantService.getAllPlants()).thenReturn(List.of(new Plant()));
        assertEquals(1, plantController.getAllPlants().size());
    }

    @Test
    public void testGetPlantById() {
        when(plantService.getPlantById(1L)).thenReturn(Optional.of(new Plant()));
        assertEquals(HttpStatus.OK, plantController.getPlantById(1L).getStatusCode());
    }

    @Test
    public void testCreatePlant() {
        Plant p = new Plant();
        when(plantService.savePlant(p)).thenReturn(p);
        assertEquals(HttpStatus.CREATED, plantController.createPlant(p).getStatusCode());
    }

    @Test
    public void testUpdatePlant() {
        Plant p = new Plant();
        when(plantService.getPlantById(1L)).thenReturn(Optional.of(p));
        when(plantService.savePlant(any())).thenReturn(p);
        assertEquals(HttpStatus.OK, plantController.updatePlant(1L, p).getStatusCode());
    }

    @Test
    public void testDeletePlant() {
        when(plantService.getPlantById(1L)).thenReturn(Optional.of(new Plant()));
        assertEquals(HttpStatus.NO_CONTENT, plantController.deletePlant(1L).getStatusCode());
    }

    @Test
    public void testWaterPlant() {
        when(plantService.addWateringLog(1L, 100)).thenReturn(new WateringLog());
        assertEquals(HttpStatus.CREATED, plantController.waterPlant(1L, 100).getStatusCode());
    }
}