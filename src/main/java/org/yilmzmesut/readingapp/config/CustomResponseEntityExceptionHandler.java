package org.yilmzmesut.readingapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.yilmzmesut.readingapp.exception.ReadingAppException;
import org.yilmzmesut.readingapp.model.response.WrapperResponse;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception, WebRequest request) {
        log.error("EntityNotFoundException : ", exception);
        return ResponseEntity.badRequest().body(WrapperResponse.builder()
                .errorDetail(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now().toString())
                .build());
    }

    @ExceptionHandler(ReadingAppException.class)
    public ResponseEntity<Object> handleReadingAppException(ReadingAppException exception, WebRequest request) {
        log.error("ReadingAppException : ", exception);
        return ResponseEntity.badRequest().body(WrapperResponse.builder()
                .errorDetail(exception.getErrorCode().getDescription())
                .errorCode(exception.getErrorCode().name())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now().toString())
                .build());
    }
}
