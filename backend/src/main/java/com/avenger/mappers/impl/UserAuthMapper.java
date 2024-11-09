package com.avenger.mappers.impl;

import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Component
public class UserAuthMapper implements Mapper<UserEntity, UserAuthDto> {

    private ModelMapper modelMapper;

    public UserAuthMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserAuthDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserAuthDto.class);
    }

    @Override
    public UserEntity mapFrom(UserAuthDto userAuthDto) {
        return modelMapper.map(userAuthDto, UserEntity.class);
    }
}
