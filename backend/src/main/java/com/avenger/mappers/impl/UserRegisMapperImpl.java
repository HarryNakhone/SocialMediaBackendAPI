package com.avenger.mappers.impl;

import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Component
public class UserRegisMapperImpl implements Mapper<UserEntity, UserRegisDto> {

    private ModelMapper modelMapper;

    public UserRegisMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;

    }
    @Override
    public UserRegisDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserRegisDto.class);
    }

    @Override
    public UserEntity mapFrom(UserRegisDto userRegisDto) {
        return modelMapper.map(userRegisDto, UserEntity.class);
    }
}
