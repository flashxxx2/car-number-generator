package ru.inovus.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inovus.test.dto.CarNumberDto;
import ru.inovus.test.entity.CarNumberEntity;
import ru.inovus.test.mapper.CarNumberMapper;
import ru.inovus.test.repository.CarNumberRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CarNumberService {

    private static final String[] LETTERS = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
    private final CarNumberRepository repository;
    private CarNumberDto carNumberDto = new CarNumberDto();

    @Autowired
    public CarNumberService(CarNumberRepository repository) {
        this.repository = repository;
    }

    public CarNumberDto generateRandomNumber() {

        Random rn = new Random();

        CarNumberDto carNumber = new CarNumberDto();
        carNumber.setNumber(1 + (int) (Math.random() * 999));

        carNumber.setFirstLetter(LETTERS[rn.nextInt(LETTERS.length)]);
        carNumber.setSecondLetter(LETTERS[rn.nextInt(LETTERS.length)]);
        carNumber.setThirdLetter(LETTERS[rn.nextInt(LETTERS.length)]);

        return carNumber;
    }

    public String nextRandom() {
        carNumberDto = generateRandomNumber();
        while (isPresent(carNumberDto)) {
            carNumberDto = generateRandomNumber();
        }
        saveCarNumber(carNumberDto);

        return carNumberDto.toString();
    }

    public String nextNumber() {
        carNumberDto = generateNextNumber();
        while (isPresent(carNumberDto)) {
            carNumberDto = generateNextNumber(carNumberDto);
        }
        saveCarNumber(carNumberDto);

        return carNumberDto.toString();
    }

    private CarNumberDto generateNextNumber() {

        CarNumberDto carNumberDto = CarNumberMapper.entityToDto(repository.findEntity());
        return generate(carNumberDto);

    }

    private CarNumberDto generateNextNumber(CarNumberDto carNumberDto) {
        return generate(carNumberDto);
    }

    private String getNextLetter(String letter) {
        return LETTERS[Arrays.asList(LETTERS).indexOf(letter) + 1];
    }

    public void saveCarNumber(CarNumberDto carNumberDto) {
        CarNumberEntity carNumber = CarNumberMapper.dtoToEntity(carNumberDto);
        repository.save(carNumber);
    }

    private boolean isPresent(CarNumberDto carNumberDto) {
        List<CarNumberEntity> carNumbers = repository.findCarNumber(carNumberDto.getFirstLetter(), carNumberDto.getSecondLetter(), carNumberDto.getThirdLetter(), carNumberDto.getNumber());
        return !carNumbers.isEmpty();
    }

    private CarNumberDto generate(CarNumberDto carNumberDto) {
        String lastLetter = LETTERS[LETTERS.length - 1];
        if (carNumberDto.getNumber() < 999) {
            carNumberDto.setNumber(carNumberDto.getNumber() + 1);
        } else if (!carNumberDto.getThirdLetter().equals(lastLetter)) {
            carNumberDto.setThirdLetter(getNextLetter(carNumberDto.getThirdLetter()));
            carNumberDto.setNumber(0);
        } else if (!carNumberDto.getSecondLetter().equals(lastLetter)) {
            carNumberDto.setSecondLetter(getNextLetter(carNumberDto.getSecondLetter()));
            carNumberDto.setThirdLetter(LETTERS[0]);
            carNumberDto.setNumber(0);
        } else if (!carNumberDto.getFirstLetter().equals(lastLetter)) {
            carNumberDto.setFirstLetter(getNextLetter(carNumberDto.getFirstLetter()));
            carNumberDto.setSecondLetter(LETTERS[0]);
            carNumberDto.setThirdLetter(LETTERS[0]);
            carNumberDto.setNumber(0);
        } else {
            carNumberDto.setNumber(0);
            carNumberDto.setFirstLetter(LETTERS[0]);
            carNumberDto.setSecondLetter(LETTERS[0]);
            carNumberDto.setThirdLetter(LETTERS[0]);
        }

        return carNumberDto;
    }

}
