package com.projectteam.coop.web.comment;

import com.projectteam.coop.service.comment.CommentService;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.post.CommentCreateForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public String create(@Login MemberSessionDto loginMember,
                         @Validated @ModelAttribute("commentForm") CommentCreateForm commentCreateForm,
                         BindingResult bindingResult,
                         @PathVariable Long postId) {

        if (bindingResult.hasErrors()) {
            if (!(bindingResult.getErrorCount() == 1 && loginMember != null && bindingResult.getFieldError("nickname") != null)) {
                return "redirect:/posts/" + postId;
            }
        }

        if (commentCreateForm.getUpperCommentId() == null) {
            commentService.addComment(commentCreateForm, loginMember);
        } else {
            commentService.addChildComment(commentCreateForm, loginMember);
        }

        return "redirect:/posts/" + postId;
    }

    @DeleteMapping("/{commentId}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("commentId") Long commentId,
                                      @RequestBody CommentCreateForm removeForm) {

        if (removeForm.getPassword() == null) {
            throw new IllegalArgumentException("댓글 비밀번호 미입력");
        }

        Map<String, Object> result = new HashMap<>();
        boolean isRemove = commentService.changeStateComment(commentId, removeForm.getPassword());
        result.put("isRemove", isRemove);
        return result;
    }
}
