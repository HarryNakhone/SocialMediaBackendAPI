package com.avenger.mappers.impl;

import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;

    }
    @Override
    public UserDto mapTo(UserEntity userEntity) {
       return modelMapper.map(userEntity, UserDto.class );
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}


