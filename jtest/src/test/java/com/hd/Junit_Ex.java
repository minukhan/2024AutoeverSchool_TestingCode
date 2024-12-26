package com.hd;

import com.hd.ex.ExClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Jnuit Exception Test")
public class Junit_Ex {
    @Test
    @DisplayName("Exception 정상 케이스")
    public void test1(){
        
        Assertions.assertThrows(RuntimeException.class, () -> {
            ExClass.func();
        });
    }
    @Test
    @DisplayName("Exception 정상 케이스 2")
    public void test2(){
        try {
            ExClass.func();
        }catch (RuntimeException e) {
            Assertions.assertEquals("Some Exception", e.getMessage());
        }
    }
}
