package com.example.jdbc.exception.advance;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckedAppTestTest {

    @Test
    void checked() {
        CheckedAppTest.Controller controller = new CheckedAppTest.Controller();

        Assertions.assertThatThrownBy(controller::request).isInstanceOf(Exception.class);
    }
}