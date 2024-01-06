package com.projectteam.coop.service.comment;

import com.projectteam.coop.domain.Board;
import com.projectteam.coop.domain.Comment;
import com.projectteam.coop.domain.Member;
import com.projectteam.coop.exception.CommentNotFoundException;
import com.projectteam.coop.exception.MisMatchedPasswordException;
import com.projectteam.coop.service.board.BoardService;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.service.post.PostService;
import com.projectteam.coop.util.SecurityUtil;
import com.projectteam.coop.web.post.CommentCreateForm;
import com.projectteam.coop.web.post.PostCreateForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional(transactionManager = "mysqlTxManager")
class CommentServiceTest {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    @Autowired
    private PostService postService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("기존 댓글에 비회원이 대댓글 등록")
    void nonmemberAddReplyComment() {

        Long boardId = boardService.addBoard(Board.createBoard());

        PostCreateForm postForm = getPostCreateForm("parentTitle", "password", "parent", "parentNickname", boardId);
        Long postId = postService.addPost(postForm, null);

        CommentCreateForm parentForm = getCommentCreateForm(postId, "1234", "comment", "commentNickname");
        Long parentCommentId = commentService.addComment(parentForm, null);

        CommentCreateForm childForm = getCommentCreateForm(postId,"1234", "child_1", "childNickname_1", parentCommentId);
        Long childCommentId1 = commentService.addChildComment(childForm, null);
        Comment childComment1 = em.find(Comment.class, childCommentId1);

        CommentCreateForm childForm2 = getCommentCreateForm(postId,"1234", "child_2", "childNickname_2", parentCommentId);
        Long childCommentId2 = commentService.addChildComment(childForm2, null);
        Comment childComment2 = em.find(Comment.class, childCommentId2);

        CommentCreateForm childForm3 = getCommentCreateForm(postId,"1234", "child_3", "childNickname_3", parentCommentId);
        Long childCommentId3 = commentService.addChildComment(childForm3, null);
        Comment childComment3 = em.find(Comment.class, childCommentId3);

        assertNotNull(childComment3.getParent(), () -> "댓글 부모가 존재해야 합니다.");
        assertNull(childComment3.getCreateMember(), () -> "작성자가 존재하면 안됩니다.");
        assertEquals("1234", childComment3.getPassword(), () -> "패스워드가 일치하지 않습니다.");
        assertEquals(Boolean.TRUE, childComment3.getStatus(), () -> "상태가 일치하지 않습니다.");
        assertEquals("child_3", childComment3.getContent(), () -> "내용이 일치하지 않습니다.");
        assertEquals("childNickname_3", childComment3.getNickname(), () -> "닉네임이 일치하지 않습니다.");
        assertEquals(parentCommentId, childComment3.getGroup(), () -> "게시물 그룹이 일치하지 않습니다.");
        assertEquals(1L, childComment3.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(1L, childComment3.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
        assertEquals(1L, childComment2.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(2L, childComment2.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
        assertEquals(1L, childComment1.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(3L, childComment1.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("기존 댓글에 회원이 대댓글 등록")
    void memberAddReplyComment() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("jjjj","test@gmail.com", "테스트", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long memberId = memberService.addMember(member);
        em.flush();
        Member findMember = memberService.findMember(memberId);
        assertEquals(member, findMember);
        MemberSessionDto loginMember = MemberSessionDto.createSession(findMember);

        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm postForm = getPostCreateForm("parentTitle", "password", "parent", "parentNickname", boardId);
        Long postId = postService.addPost(postForm, loginMember);

        CommentCreateForm parentForm = getCommentCreateForm(postId, "1234", "comment", "commentNickname");
        Long parentCommentId = commentService.addComment(parentForm, loginMember);

        CommentCreateForm childForm = getCommentCreateForm(postId,"1234", "child_1", "childNickname_1", parentCommentId);
        Long childCommentId1 = commentService.addChildComment(childForm, loginMember);
        Comment childComment1 = em.find(Comment.class, childCommentId1);

        CommentCreateForm childForm2 = getCommentCreateForm(postId,"1234", "child_2", "childNickname_2", parentCommentId);
        Long childCommentId2 = commentService.addChildComment(childForm2, loginMember);
        Comment childComment2 = em.find(Comment.class, childCommentId2);

        CommentCreateForm childForm3 = getCommentCreateForm(postId,"1234", "child_3", "childNickname_3", parentCommentId);
        Long childCommentId3 = commentService.addChildComment(childForm3, loginMember);
        Comment childComment3 = em.find(Comment.class, childCommentId3);

        assertNotNull(childComment3.getParent(), () -> "댓글 부모가 존재해야 합니다.");
        assertNotNull(childComment3.getCreateMember(), () -> "작성자가 존재해야 합니다.");
        assertEquals("1234", childComment3.getPassword(), () -> "패스워드가 일치하지 않습니다.");
        assertEquals(Boolean.TRUE, childComment3.getStatus(), () -> "상태가 일치하지 않습니다.");
        assertEquals("child_3", childComment3.getContent(), () -> "내용이 일치하지 않습니다.");
        assertEquals("테스트", childComment3.getNickname(), () -> "닉네임이 일치하지 않습니다.");
        assertEquals(parentCommentId, childComment3.getGroup(), () -> "게시물 그룹이 일치하지 않습니다.");
        assertEquals(1L, childComment3.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(1L, childComment3.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
        assertEquals(1L, childComment2.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(2L, childComment2.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
        assertEquals(1L, childComment1.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(3L, childComment1.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("게시물에 비회원이 댓글 등록")
    void nonmemberAddComment() {
        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm postForm = getPostCreateForm("parentTitle", "password", "parent", "parentNickname", boardId);
        Long postId = postService.addPost(postForm, null);

        CommentCreateForm commentForm = getCommentCreateForm(postId, "1234", "comment", "commentNickname");
        Long commentId = commentService.addComment(commentForm, null);
        Comment comment = commentService.findComment(commentId);

        assertNull(comment.getParent(), () -> "댓글 부모가 존재하면 안됩니다.");
        assertNull(comment.getCreateMember(), () -> "작성 회원이 존재하면 안됩니다.");
        assertEquals(postService.findPost(postId), comment.getPost(), () -> "댓글 게시물이 일치하지 않습니다.");
        assertEquals("1234", comment.getPassword(), () -> "패스워드가 일치하지 않습니다.");
        assertEquals(Boolean.TRUE, comment.getStatus(), () -> "상태가 일치하지 않습니다.");
        assertEquals("comment", comment.getContent(), () -> "내용이 일치하지 않습니다.");
        assertEquals("commentNickname", comment.getNickname(), () -> "닉네임이 일치하지 않습니다.");
        assertEquals(commentId, comment.getGroup(), () -> "게시물 그룹이 일치하지 않습니다.");
        assertEquals(0L, comment.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(0L, comment.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("게시물에 회원이 댓글 등록")
    void memberAddReplyPost() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("kkkk","test@gmail.com", "테스트", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long memberId = memberService.addMember(member);
        em.flush();
        Member findMember = memberService.findMember(memberId);
        assertEquals(member, findMember);
        MemberSessionDto loginMember = MemberSessionDto.createSession(findMember);

        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm postForm = getPostCreateForm("parentTitle", "password", "parent", "parentNickname", boardId);
        Long postId = postService.addPost(postForm, loginMember);

        CommentCreateForm commentForm = getCommentCreateForm(postId, "1234", "comment", "commentNickname");
        Long commentId = commentService.addComment(commentForm, loginMember);
        Comment comment = commentService.findComment(commentId);

        assertNull(comment.getParent(), () -> "댓글 부모가 존재하면 안됩니다.");
        assertNotNull(comment.getCreateMember(), () -> "작성 회원이 존재해야 합니다.");
        assertEquals(postService.findPost(postId), comment.getPost(), () -> "댓글 게시물이 일치하지 않습니다.");
        assertEquals("1234", comment.getPassword(), () -> "패스워드가 일치하지 않습니다.");
        assertEquals(Boolean.TRUE, comment.getStatus(), () -> "상태가 일치하지 않습니다.");
        assertEquals("comment", comment.getContent(), () -> "내용이 일치하지 않습니다.");
        assertEquals("테스트", comment.getNickname(), () -> "닉네임이 일치하지 않습니다.");
        assertEquals(commentId, comment.getGroup(), () -> "게시물 그룹이 일치하지 않습니다.");
        assertEquals(0L, comment.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(0L, comment.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("댓글 존재 비밀번호 일치 시 댓글 상태 변경")
    public void commentStatusChange() {
        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm postForm = getPostCreateForm("parentTitle", "password", "parent", "parentNickname", boardId);
        Long postId = postService.addPost(postForm, null);

        CommentCreateForm commentForm = getCommentCreateForm(postId, "1234", "comment", "commentNickname");
        Long commentId = commentService.addComment(commentForm, null);
        Comment comment = commentService.findComment(commentId);

        boolean isChangeComment = commentService.changeStateComment(commentId, "1234");
        assertTrue(isChangeComment);
        assertEquals(Boolean.FALSE, comment.getStatus());
    }

    @Test
    @DisplayName("댓글 존재 비밀번호 미일치 시 댓글 상태 미변경")
    public void commentStatusNotChange() {
        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm postForm = getPostCreateForm("parentTitle", "password", "parent", "parentNickname", boardId);
        Long postId = postService.addPost(postForm, null);

        CommentCreateForm commentForm = getCommentCreateForm(postId, "1234", "comment", "commentNickname");
        Long commentId = commentService.addComment(commentForm, null);
        Comment comment = commentService.findComment(commentId);

        assertThrows(MisMatchedPasswordException.class, () -> commentService.changeStateComment(commentId, null));
        assertEquals(Boolean.TRUE, comment.getStatus());
    }

    @Test
    @DisplayName("댓글 미존재 시 상태 미변경")
    public void commentNotFound() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> commentService.changeStateComment(null, "1234"));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> commentService.changeStateComment(null, null));
        CommentNotFoundException ex3 = assertThrows(CommentNotFoundException.class, () -> commentService.changeStateComment(9999L, null));
        assertEquals("댓글 아이디 미입력", ex1.getMessage());
        assertEquals("댓글 아이디 미입력", ex2.getMessage());
        assertEquals("댓글 미존재", ex3.getMessage());

    }

    private CommentCreateForm getCommentCreateForm(Long postId, String password, String comment, String commentNickname) {
        CommentCreateForm commentForm = new CommentCreateForm();
        commentForm.setPostId(postId);
        commentForm.setPassword(password);
        commentForm.setContent(comment);
        commentForm.setNickname(commentNickname);
        return commentForm;
    }

    private CommentCreateForm getCommentCreateForm(Long postId, String password, String comment, String commentNickname, Long upperCommentId) {
        CommentCreateForm commentForm = new CommentCreateForm();
        commentForm.setPostId(postId);
        commentForm.setPassword(password);
        commentForm.setContent(comment);
        commentForm.setNickname(commentNickname);
        commentForm.setUpperCommentId(upperCommentId);
        return commentForm;
    }

    private PostCreateForm getPostCreateForm(String title, String password, String content, String nickname, Long boardId) {
        PostCreateForm postForm = new PostCreateForm();
        postForm.setTitle(title);
        postForm.setPassword(password);
        postForm.setContent(content);
        postForm.setNickname(nickname);
        postForm.setBoardId(boardId);
        return postForm;
    }

}