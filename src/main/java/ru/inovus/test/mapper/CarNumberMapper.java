package ru.inovus.test.mapper;

import ru.inovus.test.dto.CarNumberDto;
import ru.inovus.test.entity.CarNumberEntity;

public class CarNumberMapper {
    public static CarNumberEntity dtoToEntity(CarNumberDto carNumberDto) {

        CarNumberEntity carNumberEntity = new CarNumberEntity();
        carNumberEntity.setFirstLetter(carNumberDto.getFirstLetter());
        carNumberEntity.setSecondLetter(carNumberDto.getSecondLetter());
        carNumberEntity.setThirdLetter(carNumberDto.getThirdLetter());
        carNumberEntity.setNumber(carNumberDto.getNumber());

        return carNumberEntity;
    }

    public static CarNumberDto entityToDto(CarNumberEntity carNumberEntity) {

        CarNumberDto carNumberDto = new CarNumberDto();
        carNumberDto.setFirstLetter(carNumberEntity.getFirstLetter());
        carNumberDto.setSecondLetter(carNumberEntity.getSecondLetter());
        carNumberDto.setThirdLetter(carNumberEntity.getThirdLetter());
        carNumberDto.setNumber(carNumberEntity.getNumber());

        return carNumberDto;
    }

}
