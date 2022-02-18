package org.yilmzmesut.readingapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    @NotNull(message = "customer id cannot be null")
    private CustomerDTO customer;
    @NotNull(message = "book id cannot be null")
    private Set<BookDTO> books;
    private Integer status;
    private BigDecimal totalPrice;
    private Date createDate;
    private Date modifyDate;
    private String createdBy;
    private String updatedBy;
}
