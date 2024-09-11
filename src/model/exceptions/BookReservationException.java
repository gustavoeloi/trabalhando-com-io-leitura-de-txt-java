package model.exceptions;

import java.io.IOException;

public class BookReservationException extends IOException {
    public BookReservationException(String msg) {
        super(msg);
    }

}
