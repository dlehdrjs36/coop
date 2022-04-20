package com.projectteam.coop.web.post;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.service.post.PostService;
import com.projectteam.coop.util.Paging;
import com.projectteam.coop.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/new")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "/templates/posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String create(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, @ModelAttribute PostForm postForm, BindingResult bindingResult) {

        //검증 로직
        if (!StringUtils.hasText(postForm.getTitle())) {
            bindingResult.addError(new FieldError("postForm", "title", postForm.getTitle(), false, new String[]{"required.post.title"}, null, null));
        }

        if (member == null) {
            if(!StringUtils.hasText(postForm.getPassword())) {
                bindingResult.addError(new FieldError("postForm", "password", postForm.getPassword(), false, new String[]{"required.post.password"}, null, null));
            } else if (postForm.getPassword().length() != 4) {
                bindingResult.addError(new FieldError("postForm", "password", postForm.getPassword(), false, new String[]{"length.post.password"}, null, null));
            }
        }

        if (bindingResult.hasErrors()) {
            return "/templates/posts/createPostForm";
        }

        Post post = Post.createPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent());
        postService.addPost(post);

        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String postList(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        Paging paging = new Paging();
        paging.calculateTotalPage(postService.totalSize());

        List<Post> findPosts = postService.findPosts(Paging.calculateStartOffset(page), Paging.calculateLastOffset(page));

        model.addAttribute("paging", paging);
        model.addAttribute("posts", findPosts);

        return "/templates/posts/postList";
    }

    @GetMapping("/posts/{postId}")
    public String info(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);

        return "/templates/posts/postInfo";
    }

    @GetMapping("/posts/{postId}/edit")
    public String updateForm(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);

        PostForm postForm = new PostForm();
        postForm.setPostId(post.getPostId());
        postForm.setTitle(post.getTitle());
        postForm.setPassword(post.getPassword());
        postForm.setContent(post.getContent());

        model.addAttribute("postForm", postForm);

        return "/templates/posts/updatePostForm";
    }

    @PostMapping("/posts/{postId}/edit")
    public String updateForm(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult, Model model) {

        //검증 로직
        if (!StringUtils.hasText(postForm.getTitle())) {
            bindingResult.addError(new FieldError("postForm", "title", postForm.getTitle(), false, new String[]{"required.post.title"}, null, null));
        }

        if (member == null) {
            if(!StringUtils.hasText(postForm.getPassword())) {
                bindingResult.addError(new FieldError("postForm", "password", postForm.getPassword(), false, new String[]{"required.post.password"}, null, null));
            } else if (postForm.getPassword().length() != 4) {
                bindingResult.addError(new FieldError("postForm", "password", postForm.getPassword(), false, new String[]{"length.post.password"}, null, null));
            }
        }

        Long postId = postService.updatePost(postForm);

        return "redirect:/posts/" + postId;
    }

}
