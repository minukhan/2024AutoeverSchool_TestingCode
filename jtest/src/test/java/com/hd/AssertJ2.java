package com.hd;

import com.hd.car.Car;
import com.hd.car.CarStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@DisplayName("AssertJ 2 Test")
public class AssertJ2 {
    Car car1;
    Car car2;
    @BeforeEach
    public void beforeEachTest() {
        car1 = Car.builder().name(null).size(1000).status(CarStatus.STOP)
                .build();
        car2 = Car.builder().name("Car1").size(1000).status(CarStatus.STOP)
                .build();
    }


    @Test
    @DisplayName("AssertJ_Test_1")
    public void test1(){
        assertThat("Hello World")
                .isNotEmpty()
                .contains("Hello")
                .doesNotContain("ABC")
                .startsWith("Hell")
                .endsWith("d")
                .isEqualTo("Hello World");
    }

    @Test
    @DisplayName("AssertJ_Test_2")
    public void test2(){
        assertThat(3.24d)
                .isPositive()
                .isGreaterThan(3) // 3보다 크냐
                .isLessThan(4)
                .isEqualTo(3.24);

    }
    @Test
    @DisplayName("AssertJ_Test_3")
    public void test3(){
//        assertThat(car1).usingComparator(
//                (a,b) -> a.getName().compareTo(b.getName())
//        ).isEqualTo(car2);
    }
    @Test
    @DisplayName("AssertJ_Test_4")
    public void test4(){
        assertThat(car1)
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .isEqualTo(car2);
//        assertThat(car2)
//                .usingRecursiveComparison()
//                .ignoringActualNullFields()
//                .isEqualTo(car1);
    }
}
