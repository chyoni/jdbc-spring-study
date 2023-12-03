package com.example.jdbc.exception.advance;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class UnCheckedAppTestTest {

    @Test
    void unchecked() {
        UnCheckedAppTest.Controller controller = new UnCheckedAppTest.Controller();

        controller.request();
        // Assertions.assertThatThrownBy(controller::request).isInstanceOf(RuntimeException.class);
    }

}