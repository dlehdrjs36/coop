package com.projectteam.coop.api;

import com.projectteam.coop.service.post.PostService;
import com.projectteam.coop.service.recommed.RecommendService;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostApiController {

    private final RecommendService recommedService;
    private final PostService postService;

    @PostMapping("/{postId}/recommend")
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
