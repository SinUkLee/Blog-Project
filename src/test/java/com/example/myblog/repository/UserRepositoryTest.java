package com.example.myblog.repository;

import com.example.myblog.entity.Post;
import com.example.myblog.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PostRemove;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void baseTimeEntityTest() {
        //given
        Post post = new Post();
        post.setTitle("a");
        post.setContent("aa");
        postRepository.save(post);

        //when
        Optional<Post> findPost = postRepository.findById(post.getId());

        //then
        System.out.println(findPost.get().getCreatedDate());
    }
}