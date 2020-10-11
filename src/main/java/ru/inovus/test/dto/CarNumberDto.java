package ru.inovus.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarNumberDto {

    private String firstLetter;
    private String secondLetter;
    private String thirdLetter;
    private int number;
    private Integer id;

    public CarNumberDto() {

    }

    public CarNumberDto(String firstLetter, String secondLetter, String thirdLetter, int number) {
        this.firstLetter = firstLetter;
        this.secondLetter = secondLetter;
        this.thirdLetter = thirdLetter;
        this.number = number;
    }

    private String addZero(int num) {
        if (num > 99) {
            return String.valueOf(num);
        } else if (num > 9) {
            return "0" + num;
        } else return "00" + num;
    }

    @Override
    public String toString() {
        return firstLetter + addZero(number) + secondLetter + thirdLetter + " " + "116 RUS";
    }
}
