package com.avenger.controllers;

import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import com.avenger.services.*;
import lombok.extern.java.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@Log
@RestController
public class FollowController {
    private FollowServices followServices;
    private Mapper<FollowEntity, FollowDto> followMapper;

    public FollowController(FollowServices followServices, Mapper<FollowEntity, FollowDto> followMapper) {
        this.followServices = followServices;
        this.followMapper = followMapper;
    }

    @PostMapping(path="/follow")
    public ResponseEntity<FollowDto> follow(@RequestBody FollowDto followDto) {
        if(followServices.isUserFollowing(followDto.getFollowerId(), followDto.getFollowingId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        FollowEntity followEntity = followMapper.mapFrom(followDto);
        followEntity.setCreateAt(LocalDateTime.now());

        FollowEntity savedFollow = followServices.createFollow(followEntity);
        return new ResponseEntity<>(followMapper.mapTo(savedFollow), HttpStatus.CREATED);
    }

    @GetMapping(path= "/follower/{id}")
    public List<FollowDto> allFollowerBasedOnId(@PathVariable("id") Long id){

        List<FollowEntity> follower = followServices.findAllWithId(id);
        return follower.stream().map(followMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/following/{id}")
    public List<FollowDto> allFollowingBasedOnId(@PathVariable("id") Long id){
        List<FollowEntity> following = followServices.findAllFollowingWithId(id);
        return following.stream().map(followMapper::mapTo).collect(Collectors.toList());
    }

    @DeleteMapping(path="/unfollow")
    public ResponseEntity<Void> unfollow(@RequestBody FollowDto followDto) {
        if(followServices.isUserFollowing(followDto.getFollowerId(), followDto.getFollowingId())) {
            followServices.delete(followDto.getFollowerId(), followDto.getFollowingId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
