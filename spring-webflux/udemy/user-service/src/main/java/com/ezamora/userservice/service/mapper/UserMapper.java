package com.ezamora.userservice.service.mapper;

import com.ezamora.userservice.domain.User;
import com.ezamora.userservice.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

  UserDto toUserDto(User user);
  User toUser(UserDto userDto);


}
