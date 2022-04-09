package com.projectteam.coop.controller;

import com.projectteam.coop.domain.Post;
import com.projectteam.coop.service.PostService;
import com.projectteam.coop.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
