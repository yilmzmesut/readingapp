package org.yilmzmesut.readingapp.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchOrderRequest {
    @NotNull(message = "createDate cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @NotNull(message = "modifyDate cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyDate;
    @Min(value = 0, message = "page can be 0 at minimum")
    private int page;
    @Min(value = 0, message = "size can be 0 at minimum")
    private int size;
}
