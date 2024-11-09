package com.avenger.services.impl;

import com.avenger.domains.*;
import com.avenger.domains.entity.*;
import com.avenger.repositories.*;
import com.avenger.services.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class LikeServiceImpl implements LikeServices {

    PostRepository postRepo;

    LikeRepository likeRepo;

    public LikeServiceImpl(LikeRepository likeRepo, PostRepository postRepo){
        this.likeRepo = likeRepo;
        this.postRepo = postRepo;
    }

    @Override
    public boolean isLikeExist(String postId, Long userId) {
        Optional<LikesEntity> likeEntity = likeRepo.findByUserIdAndPostId(userId, postId);
        return likeEntity.isPresent();
    }

    @Override
    public LikesEntity createLikes(LikesEntity likes) {
        return likeRepo.save(likes);
    }

    @Override
    public void delete(String postId, Long userId) {
        Optional<LikesEntity> likeEntity = likeRepo.findByUserIdAndPostId(userId, postId);
        likeEntity.ifPresent(likeRepo::delete);
    }

//    @Override
//    public List<LikesEntity> findAllLikesWithId(String id) {
//        if (!postRepo.existsById(id)){
//            throw new IllegalArgumentException("Post not found with this Id: " + id);
//        }
//        return StreamSupport.stream(postRepo   //// findAll() return Iterable Object, we used StreamSupport to turn that into List using toList()
//                        .findAllFollowerWithId(id)
//                        .spliterator(),
//                false).collect(Collectors.toList());
//    }





//    @Override
//    public void delete(String postId, Long userId) {
//        LikeId likeId =  new LikeId(userId, postId);
//        likeRepo.deleteById(likeId);
//
//    }
//
//    @Override
//    public LikesEntity createLikes(LikesEntity likesEntity) {
//        return likeRepo.save(likesEntity);
//    }
//
//    @Override
//    public boolean isLikeExist(String postId, Long userId) {
//        LikeId likeId =  new LikeId(userId, postId);
//        return  likeRepo.existsById(likeId);
//    }



}
