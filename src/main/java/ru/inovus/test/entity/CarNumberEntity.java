package ru.inovus.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "car_numbers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CarNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_letter")
    private String firstLetter;

    @Column(name = "second_letter")
    private String secondLetter;

    @Column(name = "third_letter")
    private String thirdLetter;

    @Column(name = "number")
    private int number;

}
