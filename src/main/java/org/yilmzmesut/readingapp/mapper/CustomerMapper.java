package org.yilmzmesut.readingapp.mapper;

import org.mapstruct.Mapper;
import org.yilmzmesut.readingapp.entity.Customer;
import org.yilmzmesut.readingapp.model.dto.CustomerDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerDTO customerDTO);

    CustomerDTO toDTO(Customer customer);

    List<Customer> toEntityList(List<CustomerDTO> customerDTOS);

    List<CustomerDTO> toDTOList(List<Customer> customers);
}
