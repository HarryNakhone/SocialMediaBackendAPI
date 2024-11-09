package com.avenger.mappers.impl;

import com.avenger.domains.dto.FollowDto;
import com.avenger.domains.entity.FollowEntity;
import com.avenger.domains.entity.UserEntity;
import com.avenger.mappers.Mapper;
import com.avenger.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.*;

@Component
public class FollowMapperImpl implements Mapper<FollowEntity, FollowDto> {
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public FollowMapperImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;

        modelMapper.typeMap(FollowEntity.class, FollowDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getFollower().getId(), FollowDto::setFollowerId);
            mapper.map(src -> src.getFollowing().getId(), FollowDto::setFollowingId);
        });
    }

    @Override
    public FollowDto mapTo(FollowEntity followEntity) {
        return modelMapper.map(followEntity, FollowDto.class);
    }

    @Override
    public FollowEntity mapFrom(FollowDto followDto) {
        UserEntity follower = userRepository.findById(followDto.getFollowerId()).orElseThrow(() -> new IllegalArgumentException("No user found with ID " + followDto.getFollowerId()));
        UserEntity following = userRepository.findById(followDto.getFollowingId()).orElseThrow(() -> new IllegalArgumentException("No user found with ID " + followDto.getFollowingId()));

        return new FollowEntity(follower, following, null);
    }
}