package com.projectteam.coop.api;

import com.projectteam.coop.service.comment.CommentService;
import com.projectteam.coop.web.post.CommentCreateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @DeleteMapping("/{commentId}")
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
