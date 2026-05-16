package org.example.zhprogkorny;

import org.example.zhprogkorny.model.Plant;
import org.example.zhprogkorny.model.Room;
import org.example.zhprogkorny.model.WateringLog;
import org.example.zhprogkorny.repository.PlantRepository;
import org.example.zhprogkorny.repository.RoomRepository;
import org.example.zhprogkorny.repository.WateringLogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ZhprogkornyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhprogkornyApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedDatabase(
            PlantRepository plantRepository,
            RoomRepository roomRepository,
            WateringLogRepository wateringLogRepository) {

        return args -> {
            if (plantRepository.count() == 0) {
                // 1. Létrehozunk egy Szobát
                Room nappali = new Room();
                nappali.setName("Nappali");
                roomRepository.save(nappali);

                // 2. Létrehozzuk a növényeket, és betesszük a Nappaliba
                Plant p1 = new Plant();
                p1.setName("Rozsa");
                p1.setWaterFrequencyDays(7);
                p1.setRoom(nappali);
                plantRepository.save(p1);

                Plant p2 = new Plant();
                p2.setName("Kaktusz");
                p2.setWaterFrequencyDays(30);
                p2.setRoom(nappali);
                plantRepository.save(p2);

                // 3. Rögzítünk egy öntözést a Fikuszhoz
                WateringLog log = new WateringLog();
                log.setPlant(p1);
                log.setAmountMl(250);
                log.setDate(LocalDate.now());
                wateringLogRepository.save(log);

                System.out.println("Tesztadatok (Szoba, Növények, Öntözések) sikeresen betöltve!");
            }
        };
    }
}