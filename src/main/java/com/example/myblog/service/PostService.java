package com.example.myblog.service;

import com.example.myblog.entity.Post;
import com.example.myblog.entity.User;
import com.example.myblog.repository.PostRepository;
import com.example.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void writePost(Post post, String username) {
        User user = userRepository.findByUsername(username);
        post.setUser(user);
        postRepository.save(post);
    }

    public Post findPostOne(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("아이디를 찾을 수 없습니다."));
    }

    public Page<Post> findPostAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> findPostAllByUsername(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return postRepository.findAllByUser(user);
    }

    public Page<Post> searchPost(String searchKeyword, Pageable pageable) {
        return postRepository.findByTitleContaining(searchKeyword, pageable);
    }

    @Transactional
    public void modifyPost(Long postId, Post post) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        findPost.setTitle(post.getTitle());
        findPost.setContent(post.getContent());
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
