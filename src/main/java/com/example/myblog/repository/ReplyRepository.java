package com.example.myblog.repository;

import com.example.myblog.entity.Post;
import com.example.myblog.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply ,Long> {

    Optional<Reply> findByPost(Post post);

    Optional<Reply> findByPostIdAndId(Long postId, Long id);
}
