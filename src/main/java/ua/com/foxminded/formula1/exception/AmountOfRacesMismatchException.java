package ua.com.foxminded.formula1.exception;

public class AmountOfRacesMismatchException extends RuntimeException {

        private static final long serialVersionUID = 100L;

        public AmountOfRacesMismatchException(String msg) {
            super(msg);
        }
}
