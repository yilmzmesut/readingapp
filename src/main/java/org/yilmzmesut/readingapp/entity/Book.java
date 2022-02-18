package org.yilmzmesut.readingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOK")
public class Book extends AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String barcode;
    private Long stock;
    private BigDecimal price;
}
