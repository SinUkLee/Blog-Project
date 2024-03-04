package com.example.myblog.controller;

import com.example.myblog.auth.PrincipalDetails;
import com.example.myblog.entity.Reply;
import com.example.myblog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{postId}/write")
    public String write(@PathVariable("postId") Long postId, Reply reply, @AuthenticationPrincipal PrincipalDetails principal ) {
        replyService.writeReply(reply, principal.getUser(), postId);
        return "redirect:/post/" + postId;
    }

    @PostMapping("/{postId}/modify/{replyId}")
    public String modify(@PathVariable("postId") Long postId, @PathVariable("replyId") Long replyId, Reply reply) {
        replyService.modifyReply(postId, replyId, reply);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/{postId}/delete/{replyId}")
    public String delete(@PathVariable("postId") Long postId, @PathVariable("replyId") Long replyId) {
        replyService.deleteReply(replyId);
        return "redirect:/post/" + postId;
    }
}
