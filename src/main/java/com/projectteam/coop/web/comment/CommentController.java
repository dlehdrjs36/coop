package com.projectteam.coop.web.comment;

import com.projectteam.coop.domain.comment.service.CommentService;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.post.CommentCreateForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                         @PathVariable Long postId,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            if (!(bindingResult.getErrorCount() == 1 && loginMember != null && bindingResult.getFieldError("nickname") != null)) {
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentForm", bindingResult);
                redirectAttributes.addFlashAttribute("commentForm", commentCreateForm);

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
}
