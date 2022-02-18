package org.yilmzmesut.readingapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    @NotEmpty(message = "books cannot be empty")
    @NotNull(message = "books cannot be null")
    @Valid
    private Set<BookOrderRequest> books;
    @NotNull(message = "customer id cannot be null")
    private Long customerId;
}
