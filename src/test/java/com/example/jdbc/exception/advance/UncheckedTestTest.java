package com.example.jdbc.exception.advance;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UncheckedTestTest {
    @Test
    void unchecked_catch() {
        UncheckedTest.Service service = new UncheckedTest.Service();
        service.callCatch();
    }

    @Test
    void unchecked_throws() {
        UncheckedTest.Service service = new UncheckedTest.Service();


        assertThatThrownBy(service::callThrows)
                .isInstanceOf(UncheckedTest.MyUncheckedException.class);
    }
}