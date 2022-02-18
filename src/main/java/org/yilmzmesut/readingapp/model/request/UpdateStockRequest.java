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
public class UpdateStockRequest {
    @NotNull(message = "book id cannot be null")
    private Long bookId;
    @NotNull(message = "stock cannot be null")
    @Min(0)
    private Long stock;
}
