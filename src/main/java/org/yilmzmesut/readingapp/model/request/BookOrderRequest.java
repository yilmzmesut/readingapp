package org.yilmzmesut.readingapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookOrderRequest {
    @NotNull(message = "bookId cannot be null")
    private Long bookId;
    @Min(value = 1, message = "quantity can be 1 at minimum")
    private Long quantity;
}
