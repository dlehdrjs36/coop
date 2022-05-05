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
import java.util.Optional;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public String create(@Login MemberSessionDto memberSessionDto,
                         @Validated @ModelAttribute("commentForm") CommentCreateForm commentCreateForm,
                         BindingResult bindingResult,
                         @PathVariable Long postId) {

        if (bindingResult.hasErrors()) {
            return "redirect:/posts/" + postId;
        }

        if (commentCreateForm.getUpperCommentId() == null) {
            commentService.addComment(commentCreateForm, Optional.ofNullable(memberSessionDto));
        } else {
            commentService.addChildComment(commentCreateForm, Optional.ofNullable(memberSessionDto));
        }

        return "redirect:/posts/" + postId;
    }

    @DeleteMapping("/{commentId}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("commentId") Long commentId,
                                      @RequestBody CommentCreateForm removeForm) {

        Map<String, Object> result = new HashMap<>();

        if (removeForm.getPassword() == null) {
            throw new IllegalArgumentException("미입력된 비밀번호");
        }

        boolean isRemove = commentService.changeStateComment(commentId, removeForm.getPassword());
        result.put("isRemove", isRemove);

        return result;
//      bindingResult.rejectValue("password", "mismatch.commentForm.password");
    }
}
