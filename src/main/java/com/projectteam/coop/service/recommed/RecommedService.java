package com.projectteam.coop.service.recommed;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.domain.Recommed;
import com.projectteam.coop.repository.post.PostRepository;
import com.projectteam.coop.repository.recommed.RecommedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class RecommedService {

    private final RecommedRepository recommedRepository;
    private final PostRepository postRepository;

    public Long addRecommed(Post post, Member member) {
        return recommedRepository.addRecommed(Recommed.createRecommed(post, member));
    }

    public void removeRecommed(Long memberId, Long postId) {
        Recommed memberRecommed = recommedRepository.findMemberRecommed(memberId, postId);
        recommedRepository.removeRecommed(memberRecommed.getId());

        Post post = postRepository.findPost(postId);
        post.recommed(recommedRepository.getPostRecommedCount(postId));
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public boolean isMemberRecommed(Long memberId, Long postId) {
        Recommed recommd = recommedRepository.findMemberRecommed(memberId, postId);
        if (recommd == null) {
            return false;
        }
        return true;
    }

    public Recommed findRecommed(Long memberId, Long postId) {
        return recommedRepository.findMemberRecommed(memberId, postId);
    }
    public Long postRecommedCount(Long postId) {
        return recommedRepository.getPostRecommedCount(postId);
    }

}
