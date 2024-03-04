package com.example.myblog.repository;

import com.example.myblog.entity.Post;
import com.example.myblog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitleContaining(String searchKeyword, Pageable pageable);

    List<Post> findAllByUser(User user);

    boolean existsByTitle(String title);
}
