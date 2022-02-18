package org.yilmzmesut.readingapp.mapper;

import org.mapstruct.Mapper;
import org.yilmzmesut.readingapp.entity.Order;
import org.yilmzmesut.readingapp.model.dto.OrderDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDTO orderDTO);

    OrderDTO toDTO(Order order);

    List<Order> toEntityList(List<OrderDTO> orderDTOS);

    List<OrderDTO> toDTOList(List<Order> orders);
}
