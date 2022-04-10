package com.projectteam.coop.controller;

import com.projectteam.coop.domain.Post;
import com.projectteam.coop.service.PostService;
import com.projectteam.coop.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String create(PostForm postForm) {
        Post post = Post.createPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent());
        postService.addPost(post);

        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String postList(@RequestParam(value = "page", required = false) Integer page, Model model) {
        if (page == null) {
            page = 1;
        }

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
    public String updateForm(@ModelAttribute("postForm") PostForm postForm, Model model) {

        Long postId = postService.updatePost(postForm);

        return "redirect:/posts/" + postId;
    }

}
