package ru.inovus.test;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.inovus.test.dto.CarNumberDto;
import ru.inovus.test.service.CarNumberService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class CarNumberServiceTest {
    @Autowired
    private CarNumberService service;

    @Test
    void checkChangeOutOfBoundNumber() {

        CarNumberDto carNumberDto = new CarNumberDto("А", "А", "Х", 9990);

        service.saveCarNumber(carNumberDto);

        assertEquals("А000ВА 116 RUS", service.nextNumber());

    }

    @Test
    void checkNumberPresentInBd() {

        CarNumberDto carNumberDto = new CarNumberDto("А", "Х", "А", 900);

        service.saveCarNumber(carNumberDto);

        assertEquals("А902ХА 116 RUS", service.nextNumber());

    }

    @Test
    void checkChangeFirstAndThirdLetter() {

        CarNumberDto carNumberDto = new CarNumberDto("С", "В", "А", 999);

        service.saveCarNumber(carNumberDto);
        String result = service.nextNumber();

        assertEquals("С000ВВ 116 RUS", result);
    }

    @Test
    void checkChangeAllLetter() {

        CarNumberDto carNumberDto = new CarNumberDto("Х", "Х", "Х", 999);

        service.saveCarNumber(carNumberDto);
        String result = service.nextNumber();

        assertEquals("А000АА 116 RUS", result);
    }

    @Test
    void checkChangeThirdLetter() {

        CarNumberDto carNumberDto = new CarNumberDto("А", "Х", "А", 999);
        service.saveCarNumber(carNumberDto);
        String result = service.nextNumber();

        assertEquals("А000ХВ 116 RUS", result);
    }

    @Test
    void checkChangeNumber() {

        CarNumberDto carNumberDto = new CarNumberDto("В", "В", "В", 990);

        service.saveCarNumber(carNumberDto);
        String result = service.nextNumber();

        assertEquals("В991ВВ 116 RUS", result);
    }

    @Test
    void checkRandomNumber() {

        CarNumberDto result = service.generateRandomNumber();

        assertNotEquals(null, result);

    }

}

