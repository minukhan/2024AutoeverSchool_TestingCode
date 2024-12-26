package com.hd;
import com.hd.ex.ExClass;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@DisplayName("AssertJ 3 Test")
public class AssertJ3 {
    @Test
    public void test1(){
        assertThatThrownBy(() -> ExClass.func())
        .isInstanceOf(RuntimeException.class);
    }
    @Test
    public void test2(){
        Throwable throwable = catchThrowable(() -> ExClass.func());
        assertThat(throwable).isInstanceOf(RuntimeException.class);
        assertThat(throwable.getMessage())
                .isEqualTo("Some Exception");
    }
    @Test
    public void test3(){
        assertThatThrownBy(() -> ExClass.func())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Some Exception")
                .hasMessageEndingWith("n")
                .hasStackTraceContaining("Some");
    }
}
