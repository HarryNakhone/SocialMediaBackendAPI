package com.avenger.services.impl;

import com.avenger.domains.entity.*;
import com.avenger.repositories.*;
import com.avenger.services.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class PostServiceImpl implements PostServices {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;

    }
    @Override
    public PostEntity createUpdatePost(String id, PostEntity postEntity) {
        postEntity.setPostId(id);
        return postRepository.save(postEntity);
    }

    @Override
    public List<PostEntity> findAll() {   //// findAll() return Iterable Object, we used StreamSupport to turn that into List using toList()
        return StreamSupport.stream(postRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<PostEntity> findOnePost(String isbn) {
        return postRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return postRepository.existsById(isbn);
    }

    @Override
    public PostEntity partialUpdateB(String id, PostEntity postEntity) {
        postEntity.setPostId(id);

        return postRepository.findById(id).map(existingPost -> {
            Optional.ofNullable(postEntity.getTitle()).ifPresent(existingPost::setTitle);
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new RuntimeException("Book Does not exists"));
    }

    @Override
    public void delete(String isbn) {
        postRepository.deleteById(isbn);
    }
}
