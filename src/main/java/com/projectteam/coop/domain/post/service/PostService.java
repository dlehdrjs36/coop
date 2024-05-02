package com.projectteam.coop.domain.post.service;

import com.projectteam.coop.domain.board.model.entity.Board;
import com.projectteam.coop.domain.comment.model.entity.Recommend;
import com.projectteam.coop.domain.member.model.entity.Member;
import com.projectteam.coop.domain.post.model.entity.Post;
import com.projectteam.coop.repository.board.BoardRepository;
import com.projectteam.coop.repository.member.MemberRepository;
import com.projectteam.coop.repository.post.PostRepository;
import com.projectteam.coop.repository.recommed.RecommendRepository;
import com.projectteam.coop.web.post.PostCreateForm;
import com.projectteam.coop.web.post.PostSearch;
import com.projectteam.coop.web.post.PostUpdateForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "mysqlTxManager")
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final RecommendRepository recommendRepository;
    private final BoardRepository boardRepository;

    public Long addReplyPost(PostCreateForm postForm, MemberSessionDto loginMember) {

        Board board = boardRepository.findBoard(postForm.getBoardId());

        Post post;
        if(loginMember == null) {
            Post upperPost = postRepository.findPost(postForm.getUpperPostId());
            postRepository.orderSort(upperPost.getGroup());
            post = Post.createReplyPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent(), postForm.getNickname(), upperPost, board);
        }else {
            MemberSessionDto session = loginMember;
            Member member = memberRepository.findMember(session.getId());
            Post upperPost = postRepository.findPost(postForm.getUpperPostId());
            postRepository.orderSort(upperPost.getGroup());
            post = Post.createReplyPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent(), member, upperPost, board);
        }

        return postRepository.addPost(post);
    }

    public Long addPost(PostCreateForm postForm, MemberSessionDto loginMember) {

        Board board = boardRepository.findBoard(postForm.getBoardId());

        Post post;
        if(loginMember == null) {
            post = Post.createPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent(), postForm.getNickname(), board);
        }else {
            MemberSessionDto session = loginMember;
            Member member = memberRepository.findMember(session.getId());
            post = Post.createPost(postForm.getTitle(), postForm.getPassword(), postForm.getContent(), member, board);
        }

        return postRepository.addPost(post);
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public List<Post> findRecommendPosts() {
        return postRepository.findRecommendPosts();
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public List<Post> findPosts(PostSearch postSearch, int offset, int size) {
        return postRepository.findPosts(postSearch, offset, size);
    }

    public Post findPost(Long id) {
        Post post = postRepository.findPost(id);
        post.addViewCount();
        return post;
    }

    public void updatePost(PostUpdateForm postForm) {
        Post post = postRepository.findPost(postForm.getPostId());
        post.changePost(post.getBoard(), postForm.getNickname(), postForm.getPassword(), postForm.getTitle(), postForm.getContent());
    }

    //게시물 추천 등록
    public void recommend(Long memberId, Long postId) {
        if (recommendRepository.findMemberRecommend(memberId, postId) == null) {
            Member member = memberRepository.findMember(memberId);
            Post post = postRepository.findPost(postId);
            recommendRepository.addRecommend(Recommend.createRecommed(post, member));
            post.recommend(recommendRepository.getPostRecommendCount(postId));
        }
    }

    //전체 게시물 수
    public int totalSize() {
        return postRepository.getTotalSize();
    }

}
