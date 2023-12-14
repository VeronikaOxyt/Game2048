package ru.sbrf.game2048.exception;

public class NotEnoughSpace extends Exception {
    public NotEnoughSpace(String errorMessage) {
        super(errorMessage);
    }
}
