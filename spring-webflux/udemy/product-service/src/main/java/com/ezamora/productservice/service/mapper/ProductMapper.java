package com.ezamora.productservice.service.mapper;

import com.ezamora.productservice.domain.Product;
import com.ezamora.productservice.service.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  ProductDto toProductDto(Product product);

  Product toProduct(ProductDto productDto);


}
