package com.projectteam.coop.service.comment;

import com.projectteam.coop.domain.Comment;
import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.exception.CommentNotFoundException;
import com.projectteam.coop.repository.comment.CommentRepository;
import com.projectteam.coop.repository.member.MemberRepository;
import com.projectteam.coop.repository.post.PostRepository;
import com.projectteam.coop.web.post.CommentCreateForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;


    public Long addComment(CommentCreateForm commentCreateForm, Optional<MemberSessionDto> memberSession) {
        Comment comment;
        Post post = postRepository.findPost(commentCreateForm.getPostId());

        if(memberSession.isEmpty()) {
            comment = Comment.createComment(post, commentCreateForm.getPassword(), commentCreateForm.getContent(), commentCreateForm.getNickname());
        }else {
            MemberSessionDto session = memberSession.get();
            Member member = memberRepository.findMember(session.getId());
            comment = Comment.createComment(post, commentCreateForm.getPassword(), commentCreateForm.getContent(), member);
        }
        return commentRepository.addComment(comment);
    }

    public Long addChildComment(CommentCreateForm commentCreateForm, Optional<MemberSessionDto> memberSession) {

        Comment comment;
        Post post = postRepository.findPost(commentCreateForm.getPostId());
        if(memberSession.isEmpty()) {
            Comment upperComment = commentRepository.findComment(commentCreateForm.getUpperCommentId());
            commentRepository.orderSort(upperComment.getId(), upperComment.getDepth());
            comment = Comment.createChildComment(post, commentCreateForm.getPassword(), commentCreateForm.getContent(), commentCreateForm.getNickname(), upperComment);
        }else {
            MemberSessionDto session = memberSession.get();
            Member member = memberRepository.findMember(session.getId());
            Comment upperComment = commentRepository.findComment(commentCreateForm.getUpperCommentId());
            commentRepository.orderSort(upperComment.getId(), upperComment.getDepth());
            comment = Comment.createChildComment(post, commentCreateForm.getPassword(), commentCreateForm.getContent(), member, upperComment);
        }

        return commentRepository.addComment(comment);
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

        Comment comment = commentRepository.findComment(commentId);
        if (comment != null) {
            if (comment.getPassword().equals(password)) {
                commentRepository.changeStateComment(comment);
                return true;
            }
        }else {
            throw new CommentNotFoundException("확인 불가능한 댓글");
        }
        return false;
    }

    public int totalSize() {
        return commentRepository.getTotalSize();
    }

}
