package com.projectteam.coop.service.comment;

import com.projectteam.coop.domain.Comment;
import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.exception.CommentNotFoundException;
import com.projectteam.coop.exception.MisMatchedPasswordException;
import com.projectteam.coop.repository.comment.CommentRepository;
import com.projectteam.coop.repository.member.MemberRepository;
import com.projectteam.coop.repository.post.PostRepository;
import com.projectteam.coop.web.post.CommentCreateForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;


    //댓글 등록
    public Long addComment(CommentCreateForm commentCreateForm, MemberSessionDto loginMember) {
        Post post = postRepository.findPost(commentCreateForm.getPostId());
        Comment comment = getComment(commentCreateForm, loginMember, post);
        return commentRepository.addComment(comment);
    }

    private Comment getComment(CommentCreateForm commentCreateForm, MemberSessionDto loginMember, Post post) {
        Comment comment;
        if (loginMember == null) {
            comment = Comment.createComment(post, commentCreateForm.getPassword(), commentCreateForm.getContent(), commentCreateForm.getNickname());
        } else {
            Member member = memberRepository.findMember(loginMember.getId());
            comment = Comment.createComment(post, commentCreateForm.getPassword(), commentCreateForm.getContent(), member);
        }
        return comment;
    }

    //대댓글 등록
    public Long addChildComment(CommentCreateForm commentCreateForm, MemberSessionDto loginMember) {
        Post post = postRepository.findPost(commentCreateForm.getPostId());
        Comment comment = getChildComment(commentCreateForm, loginMember, post);
        return commentRepository.addComment(comment);
    }

    private Comment getChildComment(CommentCreateForm commentCreateForm, MemberSessionDto loginMember, Post post) {
        Comment comment;
        Comment upperComment = commentRepository.findComment(commentCreateForm.getUpperCommentId());
        commentRepository.sortCommentsByOrder(upperComment.getId());
        if (loginMember == null) {
            comment = Comment.createChildComment(post, commentCreateForm.getPassword(), commentCreateForm.getContent(), commentCreateForm.getNickname(), upperComment);
        } else {
            Member member = memberRepository.findMember(loginMember.getId());
            comment = Comment.createChildComment(post, commentCreateForm.getPassword(), commentCreateForm.getContent(), member, upperComment);
        }
        return comment;
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<Comment> findComments(int offset, int size, Long postId) {
        return commentRepository.findComments(offset, size, postId);
    }

    public Comment findComment(Long id) {
        Comment comment = commentRepository.findComment(id);

        return comment;
    }

    //DB 삭제
    public void removeComment(Long id) {
        commentRepository.removeComment(id);
    }

    //미사용 상태 변경
    public boolean changeStateComment(Long commentId, String password) {
        if (commentId == null) {
            throw new IllegalArgumentException("댓글 아이디 미입력");
        }

        Comment comment = commentRepository.findComment(commentId);
        if (comment != null) {
            if (comment.getPassword().equals(password)) {
                commentRepository.changeStateComment(comment);
                return true;
            }else {
                throw new MisMatchedPasswordException("댓글 비밀번호 미일치");
            }
        } else {
            throw new CommentNotFoundException("댓글 미존재");
        }
    }

    public int totalSize() {
        return commentRepository.getTotalSize();
    }

}
