package com.hd;

import com.hd.car.Car;
import com.hd.car.CarStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
@DisplayName("Assert API Test")
public class Junit_Test {
    Car car1;
    Car car2;
    @BeforeEach
    public void beforeEachTest() {
        car1 = Car.builder().name("Car1").size(1000).status(CarStatus.STOP)
                .build();
        car2 = Car.builder().name("Car1").size(1000).status(CarStatus.STOP)
                .build();
    }

    @Test
    @DisplayName("Test1 정상")
    public void test1(){
        org.assertj.core.api.Assertions.assertThat(CarStatus.STOP).isEqualTo(CarStatus.STOP);
        Assertions.assertEquals(car1, car2);
        Assertions.assertNotNull(car2);
        Assertions.assertEquals(car1.getName(), car2.getName());
    }
    @Test
    @DisplayName("Test2 정상")
    public void test2(){
        Assertions.assertAll(
                ()-> Assertions.assertEquals(car1, car2),
                ()-> Assertions.assertNotNull(car2),
                ()-> Assertions.assertEquals(car1.getName(), car2.getName())
        );

    }
    @Test
    @DisplayName("Test3 정상")
    public void test3(){
        Assertions.assertEquals(car1, car2, "Not Equals");
        Assertions.assertNotSame(car1, car2, "Not Same");
    }
}
