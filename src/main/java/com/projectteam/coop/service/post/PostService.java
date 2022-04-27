package com.projectteam.coop.service.post;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Recommed;
import com.projectteam.coop.repository.member.MemberRepository;
import com.projectteam.coop.repository.recommed.RecommedRepository;
import com.projectteam.coop.web.post.PostCreateForm;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.repository.post.PostRepository;
import com.projectteam.coop.web.post.PostUpdateForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final RecommedRepository recommedRepository;

    public Long addReplyPost(PostCreateForm postForm, Optional<MemberSessionDto> memberSession) {

        Post post;
        if(memberSession.isEmpty()) {
            Post upperPost = postRepository.findPost(postForm.getUpperPostId());
            postRepository.orderSort(upperPost.getPostId(), upperPost.getDepth());
            post = Post.createReplyPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent(), postForm.getNickname(), upperPost);
        }else {
            MemberSessionDto session = memberSession.get();
            Member member = memberRepository.findMember(session.getId());
            Post upperPost = postRepository.findPost(postForm.getUpperPostId());
            postRepository.orderSort(upperPost.getPostId(), upperPost.getDepth());
            post = Post.createReplyPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent(), member, upperPost);
        }

        return postRepository.addPost(post);
    }

    public Long addPost(PostCreateForm postForm, Optional<MemberSessionDto> memberSession) {

        Post post;
        if(memberSession.isEmpty()) {
            post = Post.createPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent(), postForm.getNickname());
        }else {
            MemberSessionDto session = memberSession.get();
            Member member = memberRepository.findMember(session.getId());

            post = Post.createPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent(), member);
        }

        return postRepository.addPost(post);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<Post> findRecommendPosts() {
        return postRepository.findRecommendPosts();
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<Post> findPosts(int offset, int size) {
        return postRepository.findPosts(offset, size);
    }

    public Post findPost(Long id) {
        Post post = postRepository.findPost(id);
        post.addViewCount();

        return post;
    }

    public void updatePost(PostUpdateForm postForm) {
        Post post = postRepository.findPost(postForm.getPostId());
        post.changePost(post.getBoard(), post.getPostStatus(), postForm.getPassword(), postForm.getTitle(), postForm.getContent(), post.getViewCount(), post.getRecommendCount());
    }

    //게시물 추천 등록
    public void recommend(Long memberId, Long postId) {
        Member member = memberRepository.findMember(memberId);
        Post post = postRepository.findPost(postId);

        recommedRepository.addRecommed(Recommed.createRecommed(post, member));
        post.recommed(recommedRepository.getPostRecommedCount(postId));
    }

    public int totalSize() {
        return postRepository.getTotalSize();
    }

}
