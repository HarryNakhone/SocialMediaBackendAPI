package com.avenger.mappers.impl;

import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Component

public class PostMapperImpl implements Mapper<PostEntity, PostDto> {


    private ModelMapper modelMapper;

    public PostMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;

        modelMapper.typeMap(PostEntity.class, PostDto.class).addMappings(mapper ->{
            mapper.map(src -> src.getUser().getId(), PostDto::setUserId);
        });


    }

    @Override
    public PostDto mapTo(PostEntity postEntity) {
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostEntity mapFrom(PostDto postDto) {
        return modelMapper.map(postDto, PostEntity.class);
    }
}
