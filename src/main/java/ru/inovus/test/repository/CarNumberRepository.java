package ru.inovus.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.inovus.test.entity.CarNumberEntity;

import java.util.List;

@Repository
public interface CarNumberRepository extends JpaRepository<CarNumberEntity, Integer> {

    @Query(
            value = "SELECT * FROM car_number c WHERE c.first_letter = :first_letter " +
                    "AND c.second_letter = :second_letter " +
                    "AND c.third_letter = :third_letter " +
                    "AND c.number = :number",
            nativeQuery = true)
    List<CarNumberEntity> findCarNumber(@Param("first_letter") String firstLetter,
                                        @Param("second_letter") String secondLetter,
                                        @Param("third_letter") String thirdLetter,
                                        @Param("number") int number);

    @Query(value = "SELECT * FROM car_number WHERE id = (SELECT MAX(id) FROM car_number)", nativeQuery = true)
    CarNumberEntity findEntity();

}
