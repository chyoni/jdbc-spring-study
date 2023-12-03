package com.example.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckedTest {

    /**
     * Exception을 상속받은 예외는 체크 예외가 된다.
     * */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    static class Service {
        Repository repository = new Repository();

        /**
         * 체크 예외를 잡아서 처리하는 코드
         * */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외 처리, message: {}", e.getMessage(), e);
                e.printStackTrace();
            }
        }

        /**
         * 체크 예외를 던지는 코드
         * @throws MyCheckedException
         * */
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }

}
