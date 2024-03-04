package com.example.myblog.service;

import com.example.myblog.entity.Post;
import com.example.myblog.entity.Reply;
import com.example.myblog.entity.User;
import com.example.myblog.repository.PostRepository;
import com.example.myblog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    @Transactional
    public void writeReply(Reply reply, User user, Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        reply.setContent(reply.getContent());
        reply.setUser(user);
        reply.setPost(findPost);

        replyRepository.save(reply);
    }

    @Transactional
    public void modifyReply(Long postId, Long replyId, Reply reply) {
        Reply findReply = replyRepository.findByPostIdAndId(postId, replyId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        findReply.setContent(reply.getContent());
    }

    @Transactional
    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
