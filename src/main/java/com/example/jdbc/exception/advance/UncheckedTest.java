package com.example.jdbc.exception.advance;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UncheckedTest {

    /**
     * RuntimeException을 상속받은 예외는 언체크 예외가 된다.
     * */
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }

    /**
     * 언체크 예외는 예외를 잡거나 던지지 않아도 된다. 잡지 않으면 자동으로 밖으로 던진다.
     * */
    static class Service {
        private final Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("예외 처리 message = {}, ", e.getMessage(), e);
            }
        }

        public void callThrows() {
            repository.call();
        }
    }

    /**
     * 언체크 예외는 에러를 던질 때 throws가 생략 가능하다.
     * */
    static class Repository {
        public void call() {
            throw new MyUncheckedException("ex");
        }
    }
}
