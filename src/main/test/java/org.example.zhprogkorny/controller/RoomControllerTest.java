package org.example.zhprogkorny.controller;

import org.example.zhprogkorny.model.Room;
import org.example.zhprogkorny.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomController roomController;

    @Test
    public void testGetAllRooms() {
        // Megmondjuk a "kamu" adatbázisnak, hogy adjon vissza 1 szobát
        when(roomRepository.findAll()).thenReturn(List.of(new Room()));

        // Ellenőrizzük, hogy a Controller tényleg ezt az 1 szobát adja-e vissza
        assertEquals(1, roomController.getAllRooms().size());
    }
}