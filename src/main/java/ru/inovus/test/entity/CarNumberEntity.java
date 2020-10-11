package ru.inovus.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_number")
@Getter
@Setter

public class CarNumberEntity {

    public CarNumberEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String PAST = "116 RUS";

    @Column(name = "first_letter")
    private String firstLetter;

    @Column(name = "second_letter")
    private String secondLetter;

    @Column(name = "third_letter")
    private String thirdLetter;

    @Column(name = "number")
    private int number;

    @Override
    public String toString() {
        return firstLetter + addZero(number) + secondLetter + thirdLetter + " " + PAST;
    }

    private String addZero(int num) {
        if (num > 99) {
            return String.valueOf(num);
        } else if (num > 9) {
            return "0" + num;
        } else return "00" + num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarNumberEntity carNumber = (CarNumberEntity) o;
        return number == carNumber.number &&
                firstLetter.equals(carNumber.firstLetter) &&
                secondLetter.equals(carNumber.secondLetter) &&
                thirdLetter.equals(carNumber.thirdLetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstLetter, secondLetter, thirdLetter, number);
    }

}
