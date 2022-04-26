package com.projectteam.coop.web.post;

import com.projectteam.coop.domain.Comment;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.service.comment.CommentService;
import com.projectteam.coop.service.post.PostService;
import com.projectteam.coop.service.recommed.RecommedService;
import com.projectteam.coop.util.Paging;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final RecommedService recommedService;
    private final CommentService commentService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostCreateForm());
        return "/templates/posts/createPostForm";
    }

    @PostMapping("/new")
    public String create(@Login MemberSessionDto member, @Validated @ModelAttribute("postForm") PostCreateForm postForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors={}", bindingResult);
            return "/templates/posts/createPostForm";
        }

        postService.addPost(postForm, Optional.ofNullable(member));

        return "redirect:/posts";
    }

    @GetMapping("/reply")
    public String replyForm(@ModelAttribute("postForm") PostCreateForm postForm) {
        if (postForm.getUpperPostId() == null) {
            return "redirect:/posts";
        }

        return "/templates/posts/replyPostForm";
    }

    @PostMapping("/reply")
    public String reply(@Login MemberSessionDto member, @Validated @ModelAttribute("postForm") PostCreateForm postForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors={}", bindingResult);
            return "/templates/posts/replyPostForm";
        }

        postService.addReplyPost(postForm, Optional.ofNullable(member));

        return "redirect:/posts";
    }

    @GetMapping
    public String postList(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        Paging paging = new Paging();
        paging.calculateTotalPage(postService.totalSize());

        List<Post> findPosts = postService.findPosts(Paging.calculateStartOffset(page), Paging.calculateLastOffset(page));

        model.addAttribute("paging", paging);
        model.addAttribute("posts", findPosts);

        return "/templates/posts/postList";
    }

    @GetMapping("/{postId}")
    public String info(@RequestParam(value = "page", defaultValue = "1") Integer page, @PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);

        Paging paging = new Paging();
        paging.calculateTotalPage(commentService.totalSize());

        List<Comment> findComments = commentService.findComments(Paging.calculateStartOffset(page), Paging.calculateLastOffset(page), postId);
        model.addAttribute("comments", findComments);
        model.addAttribute("commentForm", new CommentCreateForm());

        return "/templates/posts/postInfo";
    }

    @GetMapping("/{postId}/edit")
    public String updateForm(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);

        PostUpdateForm postForm = new PostUpdateForm();
        postForm.setPostId(post.getPostId());
        postForm.setTitle(post.getTitle());
        postForm.setPassword(post.getPassword());
        postForm.setContent(post.getContent());

        model.addAttribute("postForm", postForm);

        return "/templates/posts/updatePostForm";
    }

    @PostMapping("/{postId}/edit")
    public String update(@Login MemberSessionDto member, @Validated @ModelAttribute("postForm") PostUpdateForm postForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors={}", bindingResult);
            return "/posts/" + postForm.getPostId() + "/edit";
        }

        postService.updatePost(postForm);

        return "redirect:/posts/" + postForm.getPostId();
    }

    @PostMapping("/{postId}/recommend")
    @ResponseBody
    public Map<String, Object> recommend(@Login MemberSessionDto loginMember, @PathVariable Long postId) {

        changeMemberRecommed(loginMember, postId);

        Map<String, Object> result = new HashMap<>();
        result.put("recommendCount", recommedService.postRecommedCount(postId));

        return result;
    }

    private void changeMemberRecommed(MemberSessionDto loginMember, Long postId) {
        if (loginMember != null) {
            if (recommedService.isMemberRecommed(loginMember.getId(), postId)) {
                recommedService.removeRecommed(loginMember.getId(), postId);
            } else {
                postService.recommend(loginMember.getId(), postId);
            }
        }
    }
}
