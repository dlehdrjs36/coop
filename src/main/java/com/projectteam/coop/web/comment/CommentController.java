package com.projectteam.coop.web.comment;

import com.projectteam.coop.domain.Comment;
import com.projectteam.coop.service.comment.CommentService;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.post.CommentCreateForm;
import com.projectteam.coop.web.post.PostUpdateForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
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
    public void delete(@Login MemberSessionDto memberSessionDto, @Validated @ModelAttribute("commentForm") CommentCreateForm commentCreateForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return;
        }

        Comment comment = commentService.findComment(commentCreateForm.getCommentId());
        if(commentCreateForm.getPassword().equals(comment.getPassword())) {
            commentService.removeComment(commentCreateForm.getCommentId());
        }else {
            bindingResult.rejectValue("password", "mismatch.commentForm.password");
        }
    }
}
