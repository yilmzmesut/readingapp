package org.yilmzmesut.readingapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String name;
    private String barcode;
    private Long stock;
    private BigDecimal price;
    private Date createDate;
    private Date modifyDate;
    private String createdBy;
    private String updatedBy;
}
