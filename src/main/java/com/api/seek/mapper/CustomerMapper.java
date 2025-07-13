package com.api.seek.mapper;

import com.api.seek.dto.CustomerRequestDto;
import com.api.seek.dto.CustomerResponseDto;
import com.api.seek.model.CustomerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    CustomerModel toEntity(CustomerRequestDto dto);

    CustomerResponseDto toDto(CustomerModel entity);
}
