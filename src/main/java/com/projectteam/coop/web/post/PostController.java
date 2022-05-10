package com.projectteam.coop.web.post;

import com.projectteam.coop.domain.Comment;
import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.service.comment.CommentService;
import com.projectteam.coop.service.post.PostService;
import com.projectteam.coop.service.purchaselist.PurchaseListService;
import com.projectteam.coop.service.recommed.RecommendService;
import com.projectteam.coop.util.Paging;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.login.LoginForm;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final RecommendService recommedService;
    private final CommentService commentService;
    private final PurchaseListService purchaseListService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostCreateForm());
        return "/templates/posts/createPostForm";
    }

    @PostMapping("/new")
    public String create(@Login MemberSessionDto loginMember, @Validated @ModelAttribute("postForm") PostCreateForm postForm, BindingResult bindingResult, Model model) {

        postService.addPost(postForm, loginMember);

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors={}", bindingResult);
            return "/templates/posts/createPostForm";
        }

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
    public String reply(@Login MemberSessionDto loginMember, @Validated @ModelAttribute("postForm") PostCreateForm postForm, BindingResult bindingResult) {

        postService.addReplyPost(postForm, loginMember);

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors={}", bindingResult);
            return "/templates/posts/replyPostForm";
        }

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
    public String info(@Login MemberSessionDto loginMember, @RequestParam(value = "page", defaultValue = "1") Integer page, @PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);

        if (loginMember != null) {
            Boolean recommendAt = recommedService.isMemberRecommend(loginMember.getId(), postId);
            model.addAttribute("recommendAt", recommendAt);
        }

        Paging paging = new Paging();
        paging.calculateTotalPage(commentService.totalSize());

        List<Comment> findComments = commentService.findComments(Paging.calculateStartOffset(page), Paging.calculateLastOffset(page), postId);
        Map<String, Object> memberApplyIcon = getMemberApplyIcon(findComments);

        model.addAttribute("paging", paging);
        model.addAttribute("comments", findComments);
        model.addAttribute("commentsIcon", memberApplyIcon);
        model.addAttribute("commentForm", new CommentCreateForm());

        return "/templates/posts/postInfo";
    }

    private Map<String, Object> getMemberApplyIcon(List<Comment> findComments) {
        Map<String, Object> memberApplyIcon = new HashMap();
        findComments.stream()
            .filter((comment) -> comment.getCreateMember() != null)
            .forEach((comment) -> {
                PurchaseList purchaseList = purchaseListService.memberApplyIcon(comment.getCreateMember().getEmail());
                if (purchaseList != null) {
                    memberApplyIcon.put(String.valueOf(comment.getId()), purchaseList.getProduct().getPath());
                }
            });
        return memberApplyIcon;
    }

    @GetMapping("/{postId}/edit")
    public String updateForm(@Login MemberSessionDto loginMember,
                             @PathVariable Long postId, Model model,
                             @RequestParam(value = "postPassword", required = false) String password) {
        Post post = postService.findPost(postId);

        Member postCreateMember = post.getCreateMember();
        if (loginMember == null) {
            if (postCreateMember == null) {
                if (post.getPassword().equals(password)) {
                    model.addAttribute("postForm", createUpdateForm(post));
                    return "/templates/posts/updatePostForm";
                }
            }
        }else {
            if (postCreateMember != null) {
                PostUpdateForm updateForm = createUpdateForm(post);
                updateForm.setCreateMember(postCreateMember);
                if (loginMember.getId() == postCreateMember.getId()) {
                    model.addAttribute("postForm", updateForm);
                    return "/templates/posts/updatePostForm";
                }
            }else {
                if (post.getPassword().equals(password)) {
                    model.addAttribute("postForm", createUpdateForm(post));
                    return "/templates/posts/updatePostForm";
                }
            }
        }
        return "/templates/posts/passwordForm";
    }

    private PostUpdateForm createUpdateForm(Post post) {
        PostUpdateForm postForm = new PostUpdateForm();
        postForm.setPostId(post.getPostId());
        postForm.setNickname(post.getNickname());
        postForm.setTitle(post.getTitle());
        postForm.setContent(post.getContent());
        return postForm;
    }

    @PostMapping("/{postId}/edit")
    public String update(@Login MemberSessionDto loginMember,
                         @Validated @ModelAttribute("postForm") PostUpdateForm postForm,
                         BindingResult bindingResult,
                         Model model) {

        Post post = postService.findPost(postForm.getPostId());

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors={}", bindingResult);
            return "/posts/" + postForm.getPostId() + "/edit";
        }

        Member postCreateMember = post.getCreateMember();
        if (loginMember == null) {
            if (postCreateMember == null) {
                postService.updatePost(postForm);
            }
        } else {
            if (postCreateMember != null) {
                if (loginMember.getId() == postCreateMember.getId()) {
                    postForm.setNickname(loginMember.getName());
                    postService.updatePost(postForm);
                }else {
                    model.addAttribute("loginForm", new LoginForm());
                    return "/templates/login/loginForm";
                }
            }else {
                postService.updatePost(postForm);
            }
        }

        return "redirect:/posts/" + postForm.getPostId();
    }

    @PostMapping("/{postId}/recommend")
    @ResponseBody
    public Map<String, Object> recommend(@Login MemberSessionDto loginMember, @PathVariable Long postId) {
        Map<String, Object> result = new HashMap<>();
        if (loginMember != null) {
            changeMemberRecommend(loginMember, postId);
            result.put("recommendAt", recommedService.isMemberRecommend(loginMember.getId(), postId));
        }
        result.put("recommendCount", recommedService.postRecommendCount(postId));
        return result;
    }

    private void changeMemberRecommend(MemberSessionDto loginMember, Long postId) {
        if (recommedService.isMemberRecommend(loginMember.getId(), postId)) {
            recommedService.removeRecommend(loginMember.getId(), postId);
        } else {
            postService.recommend(loginMember.getId(), postId);
        }
    }
}
