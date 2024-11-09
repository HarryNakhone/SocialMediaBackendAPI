package com.avenger.services.impl;

import com.avenger.domains.FollowId;
import com.avenger.domains.entity.FollowEntity;
import com.avenger.repositories.*;
import com.avenger.services.FollowServices;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class FollowServiceImpl implements FollowServices {
    private FollowRepository followRepository;

    private UserRepository userRepository;

    public FollowServiceImpl(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUserFollowing(Long followerId, Long followingId) {
        return followRepository.existsById(new FollowId(followerId, followingId));
    }

    @Override
    public FollowEntity createFollow(FollowEntity followEntity) {
        return followRepository.save(followEntity);
    }

    @Override
    public void delete(Long followerId, Long followingId) {
        followRepository.findById(new FollowId(followerId, followingId)).ifPresent(followRepository::delete);
    }

    @Override
    public List<FollowEntity> findAllWithId(Long id) {

        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("No User is Found with Id: "  + id);
        }
        return StreamSupport.stream(followRepository   //// findAll() return Iterable Object, we used StreamSupport to turn that into List using toList()
                        .findAllFollowerWithId(id)
                        .spliterator(),
                false).collect(Collectors.toList());
    }

    @Override
    public List<FollowEntity> findAllFollowingWithId(Long id) {

        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("No User is Found with Id: "  + id);
        }
        return StreamSupport.stream(followRepository
                .findAllFollowingWithId(id)
                .spliterator(),
                false).collect(Collectors.toList()

        );
    }
}