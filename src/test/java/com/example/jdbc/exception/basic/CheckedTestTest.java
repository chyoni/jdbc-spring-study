package com.example.jdbc.exception.basic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class CheckedTestTest {

    @Test
    void checked_catch() {
        CheckedTest.Service service = new CheckedTest.Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        CheckedTest.Service service = new CheckedTest.Service();
        assertThatThrownBy(service::callThrow)
                .isInstanceOf(CheckedTest.MyCheckedException.class);
    }
}