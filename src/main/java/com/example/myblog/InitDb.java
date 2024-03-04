package com.example.myblog;

import com.example.myblog.dto.JoinDto;
import com.example.myblog.entity.Post;
import com.example.myblog.entity.User;
import com.example.myblog.repository.PostRepository;
import com.example.myblog.repository.UserRepository;
import com.example.myblog.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final UserService userService;
        private final UserRepository userRepository;
        private final PostRepository postRepository;

        public void dbInit1() {
            JoinDto joinDto = createJoinDto("user1", "1234", "user1@naver.com");
            userService.join(joinDto);

            User user = userRepository.findByUsername(joinDto.getUsername());
            for (int i = 1; i <= 10; i++) {
                Post post = createPost(user, i);
                boolean isPost = postRepository.existsByTitle(post.getTitle());
                if (isPost) {
                    return;
                }
                postRepository.save(post);
            }
        }

        public void dbInit2() {
            JoinDto joinDto = createJoinDto("user2", "1234", "user2@naver.com");
            userService.join(joinDto);

            User user = userRepository.findByUsername(joinDto.getUsername());
            for (int i = 11; i <= 20; i++) {
                Post post = createPost(user, i);
                boolean isPost = postRepository.existsByTitle(post.getTitle());
                if (isPost) {
                    return;
                }
                postRepository.save(post);
            }
        }


        private static JoinDto createJoinDto(String username, String password, String email) {
            JoinDto joinDto = new JoinDto();
            joinDto.setUsername(username);
            joinDto.setPassword(password);
            joinDto.setEmail(email);
            return joinDto;
        }

        private static Post createPost(User user, int num) {
            Post post = new Post();
            post.setUser(user);
            post.setTitle(num + "번째 글");
            post.setContent(num + "번째 글 내용입니다.");
            return post;
        }
    }

}
