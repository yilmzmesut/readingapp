package org.yilmzmesut.readingapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    @JsonIgnore
    private String password;
    private Date createDate;
    private Date modifyDate;
    private String createdBy;
    private String updatedBy;
}
