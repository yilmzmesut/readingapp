package org.yilmzmesut.readingapp.exception;

public enum ReadingAppErrorCode {
    STOCK_NOT_AVAILABLE("Stock is not available!"),
    ENTITY_NOT_FOUND("Entity is not found"),
    EXISTING_USER("There is an account with this email."),
    ;

    private final String description;

    ReadingAppErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
