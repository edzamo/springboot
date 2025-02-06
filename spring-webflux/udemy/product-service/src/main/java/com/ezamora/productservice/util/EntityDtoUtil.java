package com.ezamora.productservice.util;

import com.ezamora.productservice.domain.Product;
import com.ezamora.productservice.service.dto.ProductDto;
import org.springframework.beans.BeanUtils;


//link about  mapper dto
// https://www.vinsguru.com/microservices-dto-to-entity-entity-to-dto-mapping-libraries-comparison/
public class EntityDtoUtil {

  public static ProductDto toDto(Product product) {
    ProductDto dto = new ProductDto();
    BeanUtils.copyProperties(product, dto);
    return dto;
  }
  public static Product toEntity(ProductDto productDto) {
    Product product = new Product();
    BeanUtils.copyProperties(productDto, product);
    return product;
  }

}
