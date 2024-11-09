package com.avenger.mappers.impl;

import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import com.avenger.repositories.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Component
public class LikeMapperImpl implements Mapper<LikesEntity, LikeDto> {


    private final ModelMapper modelMapper;

    private UserRepository userRepo;

    private PostRepository postRepo;

    public LikeMapperImpl(ModelMapper modelMapper, UserRepository userRepo, PostRepository postRepo){
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.postRepo = postRepo;

      modelMapper.typeMap(LikesEntity.class, LikeDto.class).addMappings(mapper -> {
           mapper.map(src -> src.getPost().getPostId(), LikeDto::setPostId);
      });


    }

    @Override
    public LikeDto mapTo(LikesEntity likesEntity) {
        return modelMapper.map(likesEntity, LikeDto.class);
    }

    @Override
    public LikesEntity mapFrom(LikeDto likeDto) {

        UserEntity userEntity = userRepo.findById(likeDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("User Not found with ID: " + likeDto.getUserId()));

        PostEntity postEntity = postRepo.findById(likeDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("Post Not found with ID: " + likeDto.getPostId()) );

        LikesEntity likesEntity  =new LikesEntity();
        likesEntity.setUser(userEntity);
        likesEntity.setPost(postEntity);
        return likesEntity;
    }
}
