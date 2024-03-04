package com.example.myblog.controller;

import com.example.myblog.auth.PrincipalDetails;
import com.example.myblog.entity.Post;
import com.example.myblog.service.PostService;
import com.example.myblog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/writeForm";
    }

    @PostMapping("/write")
    public String write(Post post, @AuthenticationPrincipal PrincipalDetails principal) {
        postService.writePost(post, principal.getUsername());
        return "redirect:/";
    }

    @GetMapping("/{postId}")
    public String read(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postService.findPostOne(postId));
        return "post/postDetail";
    }

    @GetMapping("/{userId}/list")
    public String findAll(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("posts", postService.findPostAllByUsername(userId));
        return "post/postList";
    }

    @GetMapping("/{postId}/modify")
    public String modifyForm(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postService.findPostOne(postId));
        return "post/modifyForm";
    }

    @PostMapping("/{postId}/modify")
    public String modify(@PathVariable("postId") Long postId, Post post) {
        postService.modifyPost(postId, post);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/{postId}/delete")
    public String delete(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }
}
