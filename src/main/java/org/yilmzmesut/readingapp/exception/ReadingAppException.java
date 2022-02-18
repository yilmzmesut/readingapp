package org.yilmzmesut.readingapp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ReadingAppException extends RuntimeException {

    private final ReadingAppErrorCode errorCode;

    public ReadingAppException(ReadingAppErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public ReadingAppException(ReadingAppErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ReadingAppException(String message, Throwable cause, ReadingAppErrorCode errorCode) {
        super(errorCode.getDescription(), cause);
        this.errorCode = errorCode;
    }

    public ReadingAppException(Throwable cause, ReadingAppErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
