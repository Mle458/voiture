package com.arkeup.poc.common.utils;

import org.mapstruct.Mapper;

import com.arkeup.poc.data.dto.UserDTO;
import com.arkeup.poc.data.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDTO mp(User user);

	User map(UserDTO dto);

}
