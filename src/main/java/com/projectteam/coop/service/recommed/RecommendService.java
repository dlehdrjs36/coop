package com.projectteam.coop.service.recommed;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.domain.Recommend;
import com.projectteam.coop.repository.post.PostRepository;
import com.projectteam.coop.repository.recommed.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class RecommendService {

    private final RecommendRepository recommedRepository;
    private final PostRepository postRepository;

    public Long addRecommend(Post post, Member member) {
        return recommedRepository.addRecommend(Recommend.createRecommed(post, member));
    }

    public void removeRecommend(Long memberId, Long postId) {
        Recommend memberRecommend = recommedRepository.findMemberRecommend(memberId, postId);
        recommedRepository.removeRecommend(memberRecommend.getId());

        Post post = postRepository.findPost(postId);
        post.recommed(recommedRepository.getPostRecommendCount(postId));
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public boolean isMemberRecommend(Long memberId, Long postId) {
        Recommend recommend = recommedRepository.findMemberRecommend(memberId, postId);
        if (recommend == null) {
            return false;
        }
        return true;
    }
    public Recommend findRecommend(Long memberId, Long postId) {
        return recommedRepository.findMemberRecommend(memberId, postId);
    }

    public Long postRecommendCount(Long postId) {
        return recommedRepository.getPostRecommendCount(postId);
    }

}
